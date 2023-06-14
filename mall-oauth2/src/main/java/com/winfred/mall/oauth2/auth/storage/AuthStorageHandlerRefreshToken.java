package com.winfred.mall.oauth2.auth.storage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author winfred958
 */
public class AuthStorageHandlerRefreshToken implements AuthStorageHandler {

  private RedisOperator redisOperator;

  public AuthStorageHandlerRefreshToken(final RedisOperator redisOperator) {
    this.redisOperator = redisOperator;
  }

  @Override
  public String getKey(final OAuth2Authorization authorization) {
    OAuth2RefreshToken refreshToken = authorization.getRefreshToken().getToken();
    return StringUtils.joinWith(":", KEY_PREFIX, OAuth2ParameterNames.REFRESH_TOKEN, refreshToken.getTokenValue());
  }

  @Override
  public void save(final OAuth2Authorization authorization) {
    OAuth2RefreshToken refreshToken = authorization.getRefreshToken().getToken();
    long between = ChronoUnit.SECONDS.between(refreshToken.getIssuedAt(), refreshToken.getExpiresAt());
    getRedisOperator()
        .save(getKey(authorization), getValue(authorization), between, TimeUnit.SECONDS);
  }

  @Override
  public RedisOperator getRedisOperator() {
    return this.redisOperator;
  }

  @Override
  public boolean hit(final OAuth2Authorization authorization) {
    return Objects.nonNull(authorization.getRefreshToken());
  }
}
