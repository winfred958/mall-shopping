package com.winfred.mall.oauth2.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContext;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.CollectionUtils;

import java.security.Principal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * 1. 上一步构建好的 AbstractAuthenticationToken 逻辑.
 * 2. 交给 spring security 进行认证.
 * 3. 构建 access_token
 * 4. 构建 refresh_token
 * 5. 持久化 authentication
 *
 * @author winfred958
 */
@Slf4j
public abstract class AbstractResourceOwnerAuthenticationProvider<T extends AbstractResourceOwnerAuthenticationToken>
    implements AuthenticationProvider {

  private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.2.1";

  private final OAuth2AuthorizationService authorizationService;

  private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

  private final AuthenticationManager authenticationManager;

  @Deprecated
  private Supplier<String> refreshTokenGenerator;

  public AbstractResourceOwnerAuthenticationProvider(final OAuth2AuthorizationService authorizationService, final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, final AuthenticationManager authenticationManager) {
    this.authorizationService = authorizationService;
    this.tokenGenerator = tokenGenerator;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    final T baseAuthentication = (T) authentication;

    OAuth2ClientAuthenticationToken auth2ClientAuthenticationToken = getAuthenticatedClientElseThrowInvalidClient(authentication);
    RegisteredClient registeredClient = auth2ClientAuthenticationToken.getRegisteredClient();

    Set<String> authorizedScopes;
    // Default to configured scopes
    if (!CollectionUtils.isEmpty(baseAuthentication.getScopes())) {
      for (String requestedScope : baseAuthentication.getScopes()) {
        if (!registeredClient.getScopes().contains(requestedScope)) {
          throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_SCOPE);
        }
      }
      authorizedScopes = new LinkedHashSet<>(baseAuthentication.getScopes());
    } else {
      throw new OAuth2AuthenticationException("scope_is_empty");
    }

    Map<String, Object> additionalAttrs = baseAuthentication.getAdditionalAttrs();

    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = buildToken(additionalAttrs);

    // 交给 spring security 进行验证, {@link AbstractUserDetailsAuthenticationProvider # retrieveUser()}
    Authentication usernamePasswordAuthentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    AuthorizationServerContext authorizationServerContext = AuthorizationServerContextHolder.getContext();
    AuthorizationGrantType authorizationGrantType = baseAuthentication.getAuthorizationGrantType();

    // @formatter:off
    DefaultOAuth2TokenContext.Builder auth2TokenContextBuilder = DefaultOAuth2TokenContext
        .builder()
        .registeredClient(registeredClient)
        .principal(usernamePasswordAuthentication)
        .authorizationServerContext(authorizationServerContext)
        .authorizedScopes(authorizedScopes)
        .authorizationGrantType(authorizationGrantType)
        .authorizationGrant(baseAuthentication);
    // @formatter:on

    OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization
        .withRegisteredClient(registeredClient)
        .principalName(usernamePasswordAuthentication.getName())
        .authorizationGrantType(baseAuthentication.getAuthorizationGrantType())
        // 0.4.0 新增的方法
        .authorizedScopes(authorizedScopes);


    // ----- Access token -----
    OAuth2TokenContext tokenContext = auth2TokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();
    OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
    if (generatedAccessToken == null) {
      OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
          "The token generator failed to generate the access token.", ERROR_URI);
      throw new OAuth2AuthenticationException(error);
    }
    OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
        generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
        generatedAccessToken.getExpiresAt(), tokenContext.getAuthorizedScopes());
    if (generatedAccessToken instanceof ClaimAccessor) {
      authorizationBuilder.id(accessToken.getTokenValue())
          .token(accessToken,
              (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME,
                  ((ClaimAccessor) generatedAccessToken).getClaims()))
          // 0.4.0 新增的方法
          .authorizedScopes(authorizedScopes)
          .attribute(Principal.class.getName(), usernamePasswordAuthentication);
    } else {
      authorizationBuilder.id(accessToken.getTokenValue()).accessToken(accessToken);
    }

    // ----- Refresh token -----
    OAuth2RefreshToken refreshToken = null;
    if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
        // Do not issue refresh token to public client
        !auth2ClientAuthenticationToken.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {

      if (this.refreshTokenGenerator != null) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt.plus(registeredClient.getTokenSettings().getRefreshTokenTimeToLive());
        refreshToken = new OAuth2RefreshToken(this.refreshTokenGenerator.get(), issuedAt, expiresAt);
      } else {
        tokenContext = auth2TokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
        OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
        if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
          OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
              "The token generator failed to generate the refresh token.", ERROR_URI);
          throw new OAuth2AuthenticationException(error);
        }
        refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
      }
      authorizationBuilder.refreshToken(refreshToken);
    }

    OAuth2Authorization authorization = authorizationBuilder.build();
    this.authorizationService.save(authorization);
    return new OAuth2AccessTokenAuthenticationToken(registeredClient, auth2ClientAuthenticationToken, accessToken,
        refreshToken, Objects.requireNonNull(authorization.getAccessToken().getClaims()));
  }

  private OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(
      Authentication authentication) {

    OAuth2ClientAuthenticationToken clientPrincipal = null;

    if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
      clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
    }

    if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
      return clientPrincipal;
    }

    throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
  }

  public abstract UsernamePasswordAuthenticationToken buildToken(Map<String, Object> additionalAttrs);
}