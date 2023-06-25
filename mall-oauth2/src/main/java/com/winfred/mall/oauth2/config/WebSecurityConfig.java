package com.winfred.mall.oauth2.config;

import com.winfred.mall.oauth2.base.MallUserDetailsAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author winfred958
 */
@EnableWebSecurity
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
    http
        .authorizeRequests(authorizeRequests -> authorizeRequests
            .antMatchers("/token/*").permitAll()// 开放自定义的部分端点
            .antMatchers("/support/**").permitAll()// 开放自定义的部分端点
            .antMatchers("/oauth2/**").permitAll()// 开放自定义的部分端点
            .anyRequest()
            .authenticated()
        )
        .headers()
        .frameOptions()
        // 避免iframe同源无法登录
        .sameOrigin()
//        .and()
//        // 个性化登录界面
//        .apply(new FormIdentityLoginConfigurer())
    ;

    // 处理 UsernamePasswordAuthenticationToken
    http
        .authenticationProvider(new MallUserDetailsAuthenticationProvider());
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
