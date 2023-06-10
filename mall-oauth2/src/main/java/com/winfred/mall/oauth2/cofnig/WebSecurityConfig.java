package com.winfred.mall.oauth2.cofnig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author winfred958
 */
@Configuration
public class WebSecurityConfig {

  /**
   * spring security 默认的安全策略
   *
   * @param http security注入点
   * @return SecurityFilterChain
   * @throws Exception
   */
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/token/*")
            .permitAll()// 开放自定义的部分端点
            .anyRequest()
            .authenticated())
        .headers().frameOptions().sameOrigin();// 避免iframe同源无法登录
    return http.build();
  }

  /**
   * 暴露静态资源
   * <p>
   * https://github.com/spring-projects/spring-security/issues/10938
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  @Order(0)
  SecurityFilterChain resources(HttpSecurity http) throws Exception {
    http.requestMatchers((matchers) -> matchers.antMatchers("/actuator/**", "/css/**", "/error"))
        .authorizeHttpRequests((authorize) -> {
          authorize.anyRequest().permitAll();
        })
        .requestCache().disable()
        .securityContext().disable()
        .sessionManagement().disable()
    ;
    return http.build();
  }
}