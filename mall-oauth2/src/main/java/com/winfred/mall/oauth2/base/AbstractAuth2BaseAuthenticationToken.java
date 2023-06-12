package com.winfred.mall.oauth2.base;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 自定义授权模式
 * <p>
 * AbstractAuthenticationToken
 *
 * @author winfred958
 */
public abstract class AbstractAuth2BaseAuthenticationToken extends AbstractAuthenticationToken {

  @Getter
  private final AuthorizationGrantType authorizationGrantType;

  @Getter
  private final Authentication authentication;

  @Getter
  private final Set<String> scopes;

  @Getter
  private final Map<String, Object> additionalAttrs;


  public AbstractAuth2BaseAuthenticationToken(AuthorizationGrantType authorizationGrantType,
                                              Authentication authentication, @Nullable Set<String> scopes,
                                              @Nullable Map<String, Object> additionalAttrs) {
    super(Collections.emptyList());
    Assert.notNull(authorizationGrantType, "authorizationGrantType cannot be null");
    Assert.notNull(authentication, "authentication cannot be null");
    this.authorizationGrantType = authorizationGrantType;
    this.authentication = authentication;
    this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
    this.additionalAttrs = Collections.unmodifiableMap(
        additionalAttrs != null ? new HashMap<>(additionalAttrs) : Collections.emptyMap());
  }

  /**
   * 获取凭证
   *
   * @return
   */
  @Override
  public Object getCredentials() {
    return this.authentication.getCredentials();
  }

  @Override
  public Object getPrincipal() {
    return this.authentication.getPrincipal();
  }
}
