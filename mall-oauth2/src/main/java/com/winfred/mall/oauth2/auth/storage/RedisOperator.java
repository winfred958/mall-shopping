package com.winfred.mall.oauth2.auth.storage;

import java.util.concurrent.TimeUnit;

/**
 * @author winfred958
 */
public interface RedisOperator {

  void save(String key, String field, Object data);

  void remove(String key, String field);

  void save(String key, Object data, long timeout, TimeUnit unit);

  void remove(String key);
}
