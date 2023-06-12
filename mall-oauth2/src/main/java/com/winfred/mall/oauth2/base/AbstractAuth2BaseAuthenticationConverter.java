package com.winfred.mall.oauth2.base;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 认证转换器
 * <p>
 * request -> authentication
 *
 * @author winfred958
 */
public abstract class AbstractAuth2BaseAuthenticationConverter<T extends AbstractAuth2BaseAuthenticationToken> implements AuthenticationConverter {


  @Override
  public Authentication convert(HttpServletRequest request) {

    return null;
  }
}
