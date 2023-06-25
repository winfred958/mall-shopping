package com.winfred.mall.oauth2.base;

import cn.hutool.extra.servlet.ServletUtil;
import com.winfred.mall.oauth2.utils.WebUtils;
import lombok.SneakyThrows;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 核心认证逻辑
 * 1. 调用 UserDetailsService 查询用户信息.
 * 2. 校验用户名, 密码, 状态.
 *
 * @author winfred958
 */
public class MallUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  private PasswordEncoder passwordEncoder;

  private volatile String userNotFoundEncodedPassword;

  private UserDetailsService userDetailsService;

  private UserDetailsPasswordService userDetailsPasswordService;


  public MallUserDetailsAuthenticationProvider() {
    setMessageSource(new SpringSecurityMessageSource());
    setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
  }

  /**
   * 校验用户信息, 密码, 状态
   *
   * @param userDetails
   * @param authentication
   * @throws AuthenticationException
   */
  @Override
  protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    // 调用 UserDetailsService 获得
    final String password = userDetails.getPassword();
    final String presentedPassword = String.valueOf(authentication.getCredentials());

  }

  /**
   * 默认实现: 调用 UserDetailsService 获取用户信息
   *
   * @param username
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @SneakyThrows
  @Override
  protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    String name = authentication.getName();
    HttpServletRequest request = WebUtils.getRequest()
        .orElseThrow(
            (Supplier<Throwable>) () -> new InternalAuthenticationServiceException("web request is empty"));

    Map<String, String> paramMap = ServletUtil.getParamMap(request);
    String grantType = paramMap.get(OAuth2ParameterNames.GRANT_TYPE);
    String clientId = paramMap.get(OAuth2ParameterNames.CLIENT_ID);

    return null;
  }


  /**
   * Sets the PasswordEncoder instance to be used to encode and validate passwords. If
   * not set, the password will be compared using
   * {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
   *
   * @param passwordEncoder must be an instance of one of the {@code PasswordEncoder}
   *                        types.
   */
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
    this.passwordEncoder = passwordEncoder;
    this.userNotFoundEncodedPassword = null;
  }

  protected PasswordEncoder getPasswordEncoder() {
    return this.passwordEncoder;
  }

  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  protected UserDetailsService getUserDetailsService() {
    return this.userDetailsService;
  }

  public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
    this.userDetailsPasswordService = userDetailsPasswordService;
  }

  public String getUserNotFoundEncodedPassword() {
    return userNotFoundEncodedPassword;
  }

  public void setUserNotFoundEncodedPassword(final String userNotFoundEncodedPassword) {
    this.userNotFoundEncodedPassword = userNotFoundEncodedPassword;
  }

  public UserDetailsPasswordService getUserDetailsPasswordService() {
    return userDetailsPasswordService;
  }
}
