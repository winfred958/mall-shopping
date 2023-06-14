package com.winfred.mall.oauth2.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.winfred.common.constant.MallConstant;
import com.winfred.mall.oauth2.handler.MallAuthenticationFailureHandler;
import com.winfred.mall.oauth2.handler.MallAuthenticationSuccessHandler;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationEndpointConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2ClientAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author winfred958
 */
@Configuration
public class AuthorizationServerSecurityConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(DataSource.class)
  public JdbcTemplate buildJdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  /**
   * 自定义 token 认证
   *
   * @param httpSecurity
   * @return
   * @throws Exception
   */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
    OAuth2AuthorizationServerConfigurer securityConfigurer = httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class);

    securityConfigurer
        .tokenEndpoint(new Customizer<OAuth2TokenEndpointConfigurer>() {

          @Override
          public void customize(OAuth2TokenEndpointConfigurer auth2TokenEndpointConfigurer) {
            // 个性化认证授权端点
            auth2TokenEndpointConfigurer
                // 注入自定义的授权认证Converter
                .accessTokenRequestConverter(accessTokenRequestConverter())
                .accessTokenResponseHandler(new MallAuthenticationSuccessHandler())
                .errorResponseHandler(new MallAuthenticationFailureHandler());
          }
        })
        .clientAuthentication(new Customizer<OAuth2ClientAuthenticationConfigurer>() {

          // 个性化客户端认证
          @Override
          public void customize(OAuth2ClientAuthenticationConfigurer oAuth2ClientAuthenticationConfigurer) {
            oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new MallAuthenticationFailureHandler());
          }
        })
        // 处理客户端认证异常
        .authorizationEndpoint(
            new Customizer<OAuth2AuthorizationEndpointConfigurer>() {

              // 授权码端点个性化confirm页面
              @Override
              public void customize(OAuth2AuthorizationEndpointConfigurer oAuth2AuthorizationEndpointConfigurer) {
                oAuth2AuthorizationEndpointConfigurer.consentPage(MallConstant.CUSTOM_CONSENT_PAGE_URI_CONFIRM_ACCESS);
              }
            }
        );


    return httpSecurity.build();
  }

  private AuthenticationConverter accessTokenRequestConverter() {
    return new DelegatingAuthenticationConverter(Arrays.asList(
        new OAuth2RefreshTokenAuthenticationConverter(),
        new OAuth2ClientCredentialsAuthenticationConverter(),
        new OAuth2AuthorizationCodeAuthenticationConverter(),
        new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
  }

  @Bean
  public JWKSource<?> jwkSource(KeyPair keyPair) {
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
    RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
    // @formatter:off
    RSAKey rsaKey = new RSAKey.Builder(publicKey)
        .privateKey(privateKey)
        .keyID(UUID.randomUUID().toString())
        .build();
    // @formatter:on
    JWKSet jwkSet = new JWKSet(rsaKey);
    return new ImmutableJWKSet<>(jwkSet);
  }

  @Bean
  public JwtDecoder jwtDecoder(KeyPair keyPair) {
    return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
  }

  @Bean
  public ProviderSettings providerSettings() {
    return ProviderSettings
        .builder()
        .issuer("http://oauth2.local.xyz:9000/mall-oauth2")
        .build();
  }

  @Bean
  @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
  KeyPair generateRsaKey() {
    KeyPair keyPair;
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      keyPair = keyPairGenerator.generateKeyPair();
    } catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
    return keyPair;
  }
}
