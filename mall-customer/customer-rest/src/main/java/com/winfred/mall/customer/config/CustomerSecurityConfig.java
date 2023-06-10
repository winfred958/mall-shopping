package com.winfred.mall.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Arrays;
import java.util.List;

/**
 * @author winfred958
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(proxyTargetClass = true)
//@EnableOAuth2Client
public class CustomerSecurityConfig {

  private static final List<String> AUTH_WHITELIST = Arrays.asList(
      "/v2/api-docs",
      "/configuration/ui",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
  );

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeExchange(authorize -> {
          authorize
              .pathMatchers(AUTH_WHITELIST.toArray(new String[0])).permitAll()
              // FIXME: test
              .pathMatchers("/user/*").permitAll()
          ;
        });

//    httpSecurity
//        .oauth2ResourceServer((oauth2ResourceServer) ->
//            oauth2ResourceServer
//                .jwt((jwt) ->
//                    jwt
//                        .publicKey(publicKey())
//                )
//        );
    return httpSecurity.build();
  }


}
