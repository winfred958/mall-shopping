package com.winfred.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;


@Configuration
@Slf4j
public class UserDefineGatewayFilter {

  /**
   * echo took
   *
   * @return
   */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public GlobalFilter echoTookFilter() {
    return new GlobalFilter() {
      @Override
      public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        long start = System.currentTimeMillis();
        return chain
            .filter(exchange)
            .then(Mono.fromRunnable(() -> {
              long end = System.currentTimeMillis();
              log.info("request took {} => rui  {}", end - start, uri);
            }));
      }
    };
  }

}
