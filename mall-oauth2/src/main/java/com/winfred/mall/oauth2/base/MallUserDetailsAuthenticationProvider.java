package com.winfred.mall.oauth2.base;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 核心认证逻辑
 * 1. 调用 UserDetailsService 查询用户信息.
 * 2. 校验用户名, 密码, 状态.
 *
 * @author winfred958
 */
public class MallUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


  /**
   * 校验用户信息, 密码, 状态
   *
   * @param userDetails
   * @param authentication
   * @throws AuthenticationException
   */
  @Override
  protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

  }

  /**
   * 默认实现: 调用 UserDetailsService 获取用户信息
   *
   * @param username
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    String name = authentication.getName();

    return null;
  }

}
