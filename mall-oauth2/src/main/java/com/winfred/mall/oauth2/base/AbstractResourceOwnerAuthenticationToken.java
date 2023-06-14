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
public abstract class AbstractResourceOwnerAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = -1836148487179340083L;

  @Getter
  private final AuthorizationGrantType authorizationGrantType;

  @Getter
  private final Authentication authentication;

  @Getter
  private final Set<String> scopes;

  @Getter
  private final Map<String, Object> additionalAttrs;


  public AbstractResourceOwnerAuthenticationToken(AuthorizationGrantType authorizationGrantType,
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
   * 凭证
   * <p>
   * 可以是密码或其他实现
   *
   * @return
   */
  @Override
  public Object getCredentials() {
    return this.authentication.getCredentials();
  }

  /**
   * 通常为用户名
   *
   * @return
   */
  @Override
  public Object getPrincipal() {
    return this.authentication.getPrincipal();
  }
}
