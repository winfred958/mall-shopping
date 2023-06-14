package com.winfred.mall.oauth2.auth.storage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.Objects;

/**
 * @author winfred958
 */
public class AuthStorageHandlerState implements AuthStorageHandler {

  private RedisOperator redisOperator;

  public AuthStorageHandlerState(final RedisOperator redisOperator) {
    this.redisOperator = redisOperator;
  }

  @Override
  public RedisOperator getRedisOperator() {
    return this.redisOperator;
  }

  @Override
  public String getKey(final OAuth2Authorization authorization) {
    String token = authorization.getAttribute(OAuth2ParameterNames.STATE);
    return StringUtils.joinWith(":", KEY_PREFIX, OAuth2ParameterNames.STATE, token);
  }

  @Override
  public boolean hit(final OAuth2Authorization authorization) {
    return Objects.nonNull(authorization.getAttribute(OAuth2ParameterNames.STATE));
  }
}
