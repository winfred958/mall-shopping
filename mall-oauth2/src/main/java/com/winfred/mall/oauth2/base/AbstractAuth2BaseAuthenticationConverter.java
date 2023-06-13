package com.winfred.mall.oauth2.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * 自定义 认证转换器
 * <p>
 * request -> authentication
 *
 * @author winfred958
 */
public abstract class AbstractAuth2BaseAuthenticationConverter<T extends AbstractAuth2BaseAuthenticationToken> implements AuthenticationConverter {


  /**
   * 构造自定义 token
   *
   * @param authentication
   * @param scopes
   * @param additionalAttrs
   * @return
   */
  public abstract T buildToken(Authentication authentication, Set<String> scopes, Map<String, Object> additionalAttrs);

  @Override
  public Authentication convert(HttpServletRequest request) {

    return null;
  }
}
