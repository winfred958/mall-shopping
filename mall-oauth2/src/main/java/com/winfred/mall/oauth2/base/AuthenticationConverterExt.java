package com.winfred.mall.oauth2.base;

import javax.servlet.http.HttpServletRequest;

/**
 * @author winfred958
 */
public interface AuthenticationConverterExt {

  /**
   * 是否支持的认证模式
   *
   * @param request
   * @return
   */
  boolean support(HttpServletRequest request);
}
