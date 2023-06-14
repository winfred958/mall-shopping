package com.winfred.mall.oauth2.base.password;

import com.winfred.mall.oauth2.base.AbstractResourceOwnerAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.util.Map;

/**
 * @author winfred958
 */
@Slf4j
public class ResourceOwnerPasswordAuthenticationProvider extends AbstractResourceOwnerAuthenticationProvider<ResourceOwnerPasswordAuthenticationToken> {

  public ResourceOwnerPasswordAuthenticationProvider(final OAuth2AuthorizationService authorizationService, final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, final AuthenticationManager authenticationManager) {
    super(authorizationService, tokenGenerator, authenticationManager);
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    boolean assignableFrom = ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
    log.debug("supports authentication={} assignableFrom:{} ", authentication, assignableFrom);
    return assignableFrom;
  }

  @Override
  public UsernamePasswordAuthenticationToken buildToken(final Map<String, Object> additionalAttrs) {
    String username = (String) additionalAttrs.get(OAuth2ParameterNames.USERNAME);
    String password = (String) additionalAttrs.get(OAuth2ParameterNames.PASSWORD);
    return new UsernamePasswordAuthenticationToken(username, password);
  }
}
