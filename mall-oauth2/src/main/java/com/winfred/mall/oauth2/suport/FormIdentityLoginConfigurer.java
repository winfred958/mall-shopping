package com.winfred.mall.oauth2.suport;

import com.winfred.mall.oauth2.handler.MallAuthenticationFailureHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * 个性化登录
 *
 * @author winfred958
 */
public class FormIdentityLoginConfigurer extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

  @Override
  public void init(final HttpSecurity http) throws Exception {
    http.formLogin(formLogin -> {
          formLogin.loginPage("/token/login");
          formLogin.loginProcessingUrl("/token/form");
          formLogin.failureHandler(new MallAuthenticationFailureHandler());

        })
        .logout() // SSO登出成功处理
        .logoutSuccessHandler(new SsoLogoutSuccessHandler())
        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .and()
        .csrf()
        .disable();
  }
}
