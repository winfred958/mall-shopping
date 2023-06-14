package com.winfred.mall.oauth2.auth.storage;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;

import java.util.concurrent.TimeUnit;

/**
 * @author winfred958
 */
public interface AuthStorageHandler {


  String KEY_PREFIX = "token:oauth2";

  Long DEFAULT_TIME_OUT_SECOND = 600L;

  /**
   * key
   *
   * @param authorization
   * @return
   */
  String getKey(final OAuth2Authorization authorization);

  /**
   * hset 的 field
   *
   * @param authorization
   * @return
   */
  default String getField(final OAuth2Authorization authorization) {
    return null;
  }

  default Object getValue(final OAuth2Authorization authorization) {
    return JSON.toJSONString(authorization, JSONWriter.Feature.MapSortField);
  }

  RedisOperator getRedisOperator();

  /**
   * 保存授权信息
   *
   * @param authorization
   */
  default void save(final OAuth2Authorization authorization) {
    RedisOperator redisOperator = getRedisOperator();
    String key = getKey(authorization);
    String field = getField(authorization);
    Object value = getValue(authorization);
    if (field == null) {
      redisOperator.save(key, value, DEFAULT_TIME_OUT_SECOND, TimeUnit.SECONDS);
    } else {
      redisOperator.save(key, field, value);
    }
  }

  /**
   * 移除授权信息
   *
   * @param authorization
   */
  default void remove(final OAuth2Authorization authorization) {
    RedisOperator redisOperator = getRedisOperator();
    String key = getKey(authorization);
    String field = getField(authorization);
    if (field == null) {
      redisOperator.remove(key);
    } else {
      redisOperator.remove(key, field);
    }
  }

  /**
   * 是否处理
   *
   * @param authorization
   * @return
   */
  boolean hit(final OAuth2Authorization authorization);
}
