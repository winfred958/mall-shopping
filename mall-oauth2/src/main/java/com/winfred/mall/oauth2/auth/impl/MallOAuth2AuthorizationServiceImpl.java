package com.winfred.mall.oauth2.auth.impl;

import com.alibaba.fastjson2.JSON;
import com.winfred.mall.oauth2.auth.MallOAuth2AuthorizationService;
import com.winfred.mall.oauth2.auth.storage.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

import static com.winfred.mall.oauth2.auth.storage.AuthStorageHandler.KEY_PREFIX;

/**
 * OAuth2 授权实现
 * <p>
 * 需要缓存实现
 *
 * @author winfred958
 */
@Component
public class MallOAuth2AuthorizationServiceImpl implements MallOAuth2AuthorizationService {

  private AuthStoragePipeline PIPELINE = null;
  private final RedisTemplate redisTemplate;

  @Autowired
  public MallOAuth2AuthorizationServiceImpl(final RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
    RedisOperator redisOperator = new RedisOperator() {
      @Override
      public void save(final String key, final String field, final Object data) {
      }

      @Override
      public void remove(final String key, final String field) {

      }

      @Override
      public void save(final String key, final Object data, final long timeout, final TimeUnit unit) {
        redisTemplate
            .opsForValue()
            .set(key, data, timeout, unit);
      }

      @Override
      public void remove(final String key) {
        redisTemplate
            .delete(key);
      }
    };

    PIPELINE = AuthStoragePipeline
        .build()
        .addHandler(new AuthStorageHandlerState(redisOperator))
        .addHandler(new AuthStorageHandlerCode(redisOperator))
        .addHandler(new AuthStorageHandlerRefreshToken(redisOperator))
        .addHandler(new AuthStorageHandlerAccessToken(redisOperator));
  }

  @Override
  public void save(final OAuth2Authorization authorization) {
    PIPELINE.doSave(authorization);
  }

  @Override
  public void remove(final OAuth2Authorization authorization) {
    PIPELINE.doRemove(authorization);
  }

  @Override
  public OAuth2Authorization findById(final String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public OAuth2Authorization findByToken(final String token, final OAuth2TokenType tokenType) {
    /**
     * 1. 从存储中获取 token.
     * 2. 比对, 校验.
     * 3. 构造, 添加角色信息
     */
    Assert.hasText(token, "token cannot be empty");
    Assert.notNull(tokenType, "tokenType cannot be empty");
    String key = StringUtils.joinWith(":", KEY_PREFIX, tokenType.getValue(), token);
    Object str = redisTemplate.opsForValue().get(key);
    return JSON.parseObject(String.valueOf(str), OAuth2Authorization.class);
  }
}
