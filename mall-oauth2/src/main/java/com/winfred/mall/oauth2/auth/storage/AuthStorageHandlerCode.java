package com.winfred.mall.oauth2.auth.storage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author winfred958
 */
public class AuthStorageHandlerCode implements AuthStorageHandler {

  private RedisOperator redisOperator;

  public AuthStorageHandlerCode(final RedisOperator redisOperator) {
    this.redisOperator = redisOperator;
  }

  @Override
  public String getKey(final OAuth2Authorization authorization) {
    OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
        .getToken(OAuth2AuthorizationCode.class);
    OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
    return StringUtils.joinWith(":", KEY_PREFIX, OAuth2ParameterNames.CODE, authorizationCodeToken.getTokenValue());
  }

  @Override
  public void save(final OAuth2Authorization authorization) {
    OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
        .getToken(OAuth2AuthorizationCode.class);
    OAuth2AuthorizationCode authorizationCodeToken = authorizationCode.getToken();
    long between = ChronoUnit.SECONDS.between(authorizationCodeToken.getIssuedAt(), authorizationCodeToken.getExpiresAt());

    RedisOperator redisOperator = getRedisOperator();
    redisOperator.save(getKey(authorization), getValue(authorization), between, TimeUnit.SECONDS);
  }

  @Override
  public RedisOperator getRedisOperator() {
    return this.redisOperator;
  }

  @Override
  public boolean hit(final OAuth2Authorization authorization) {
    OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization
        .getToken(OAuth2AuthorizationCode.class);
    return Objects.nonNull(authorizationCode);
  }
}
