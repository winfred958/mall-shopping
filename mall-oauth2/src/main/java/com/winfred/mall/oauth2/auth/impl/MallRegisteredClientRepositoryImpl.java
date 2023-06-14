package com.winfred.mall.oauth2.auth.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.winfred.common.constant.MallConstant;
import com.winfred.mall.oauth2.auth.MallRegisteredClientRepository;
import com.winfred.mall.oauth2.entity.Oauth2RegisteredClientEntity;
import com.winfred.mall.oauth2.service.IOauth2RegisteredClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2 Server Client repository 实现
 *
 * @author winfred958
 */
@Component
public class MallRegisteredClientRepositoryImpl implements MallRegisteredClientRepository {

  @Autowired
  private IOauth2RegisteredClientService oauth2RegisteredClientService;

  @Override
  public void save(final RegisteredClient registeredClient) {

  }

  @Override
  public RegisteredClient findById(final String id) {
    Oauth2RegisteredClientEntity registeredClientEntity = oauth2RegisteredClientService.getById(id);
    return buildRegisteredClient(registeredClientEntity);
  }

  @Override
  public RegisteredClient findByClientId(final String clientId) {
    QueryWrapper<Oauth2RegisteredClientEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq(Oauth2RegisteredClientEntity.CLIENT_ID, clientId);
    Oauth2RegisteredClientEntity registeredClientEntity = oauth2RegisteredClientService
        .list(queryWrapper)
        .stream()
        .findFirst()
        .orElseGet(() -> {
          return null;
        });
    return buildRegisteredClient(registeredClientEntity);
  }

  /**
   * 构造 RegisteredClient
   *
   * @param entity {@link Oauth2RegisteredClientEntity}
   * @return {@link RegisteredClient}
   */
  private RegisteredClient buildRegisteredClient(final Oauth2RegisteredClientEntity entity) {
    if (null == entity) {
      return null;
    }
    String id = String.valueOf(entity.getId());
    RegisteredClient.Builder builder = RegisteredClient
        .withId(id)
        .clientId(entity.getClientId())
        .clientIdIssuedAt(localTime2Instant(entity.getClientIdIssuedAt()))
        .clientSecret(entity.getClientSecret())
        .clientSecretExpiresAt(localTime2Instant(entity.getClientSecretExpiresAt()))
        .clientName(entity.getClientName());

    // Methods
    Set<String> methodStrSet = str2HashSet(entity.getClientAuthenticationMethods());
    if (!CollectionUtils.isEmpty(methodStrSet)) {
      methodStrSet
          .stream()
          .map(ClientAuthenticationMethod::new)
          .forEach(builder::clientAuthenticationMethod);
    }

    // GrantType
    Set<String> grantTypeStrSet = str2HashSet(entity.getAuthorizationGrantTypes());
    if (!CollectionUtils.isEmpty(grantTypeStrSet)) {
      grantTypeStrSet
          .stream()
          .map(AuthorizationGrantType::new)
          .forEach(builder::authorizationGrantType);
    }

    // redirect url
    String redirectUris = entity.getRedirectUris();
    Set<String> redirectUrlSet = str2HashSet(redirectUris);
    if (!CollectionUtils.isEmpty(redirectUrlSet)) {
      redirectUrlSet.forEach(builder::redirectUri);
    }

    // scope
    Set<String> scopeStrSet = str2HashSet(entity.getScopes());
    if (!CollectionUtils.isEmpty(scopeStrSet)) {
      scopeStrSet
          .forEach(builder::scope);
    }

    // client settings
    Map<String, Object> settingsMap = str2Map(entity.getClientSettings());
    builder.clientSettings(ClientSettings.withSettings(settingsMap).build());

    // token settings
    Map<String, Object> tokenSettingsMap = str2Map(entity.getTokenSettings());
    builder.tokenSettings(TokenSettings.withSettings(tokenSettingsMap).build());

    return builder.build();
  }

  private Set<String> str2HashSet(final String redirectUris) {
    return JSON.parseObject(redirectUris, new TypeReference<HashSet<String>>() {
    });
  }

  private Map<String, Object> str2Map(final String redirectUris) {
    return JSON.parseObject(redirectUris, new TypeReference<Map<String, Object>>() {
    });
  }

  private Instant localTime2Instant(final LocalDateTime clientIdIssuedAt) {
    if (null == clientIdIssuedAt) {
      return Instant.now();
    }
    return clientIdIssuedAt.atZone(MallConstant.ZONE_ID).toInstant();
  }

}
