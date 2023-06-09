package com.winfred.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpCookie;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@ConditionalOnProperty(prefix = "switch", name = "token.check", havingValue = "true")
@Configuration
@Slf4j
public class MyAuthFilter {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE + 1)
  public GlobalFilter cookiesFilter() {
    return new GlobalFilter() {
      @Override
      public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        log.info("cookies.size = {}", cookies.size());

        return chain
            .filter(exchange);
      }
    };
  }
}
