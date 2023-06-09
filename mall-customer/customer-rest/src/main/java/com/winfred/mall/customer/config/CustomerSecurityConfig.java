package com.winfred.mall.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author winfred958
 */
@Configuration
@EnableWebFluxSecurity
//@EnableGlobalMethodSecurity
public class CustomerSecurityConfig {

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeExchange(authorize -> {
          authorize.pathMatchers("/user/*").permitAll();
        });
    return httpSecurity.build();
  }
}
