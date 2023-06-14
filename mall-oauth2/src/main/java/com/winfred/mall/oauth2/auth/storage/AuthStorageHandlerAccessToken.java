package com.winfred.mall.oauth2.auth.storage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author winfred958
 */
public class AuthStorageHandlerAccessToken implements AuthStorageHandler {

  private RedisOperator redisOperator;

  public AuthStorageHandlerAccessToken(final RedisOperator redisOperator) {
    this.redisOperator = redisOperator;
  }

  @Override
  public String getKey(final OAuth2Authorization authorization) {
    OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
    return StringUtils.joinWith(":", KEY_PREFIX, OAuth2ParameterNames.REFRESH_TOKEN, accessToken.getTokenValue());
  }

  @Override
  public void save(final OAuth2Authorization authorization) {
    OAuth2AccessToken accessToken = authorization.getAccessToken().getToken();
    long between = ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt());
    getRedisOperator()
        .save(getKey(authorization), getValue(authorization), between, TimeUnit.SECONDS);
  }

  @Override
  public RedisOperator getRedisOperator() {
    return this.redisOperator;
  }

  @Override
  public boolean hit(final OAuth2Authorization authorization) {
    return Objects.nonNull(authorization.getAccessToken());
  }
}
