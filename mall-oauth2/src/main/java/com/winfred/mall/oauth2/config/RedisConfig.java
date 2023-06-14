package com.winfred.mall.oauth2.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author winfred958
 */
//@Configuration
public class RedisConfig {

  private final RedisTemplate<Serializable, Object> redisTemplate;

  public RedisConfig(final RedisTemplate<Serializable, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @ConditionalOnMissingClass
  @Bean
  public RedisTemplate<Serializable, Object> redisTemplateInit() {
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setHashKeySerializer(stringRedisSerializer);

    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
    return redisTemplate;
  }
}
