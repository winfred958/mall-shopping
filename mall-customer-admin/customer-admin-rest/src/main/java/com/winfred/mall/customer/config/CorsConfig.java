package com.winfred.mall.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author winfred
 */
@Configuration
public class CorsConfig {

  @Bean
  @Order(-200) //非常重要，一定要早于AuthFilter
  public CorsWebFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();

    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    // config.addAllowedMethod("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("HEAD");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("DELETE");
    config.addAllowedMethod("PATCH");
    // 允许 cookie 跨域
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
    source.registerCorsConfiguration("/**", config);

    return new CorsWebFilter(source);
  }
}
