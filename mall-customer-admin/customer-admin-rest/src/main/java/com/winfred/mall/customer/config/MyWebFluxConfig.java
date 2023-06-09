package com.winfred.mall.customer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebFilter;

import java.util.stream.Collectors;

/**
 * @author kai.hu@shuyun.com
 */
@Slf4j
@Configuration
public class MyWebFluxConfig implements WebFluxConfigurer {

  private final ObjectMapper objectMapper;

  @Value(value = "${spring.webflux.base-path}")
  private String contextPath;

  @Autowired
  public MyWebFluxConfig(@Qualifier("globalJacksonMapper") ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
    configurer
        .defaultCodecs()
        .jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
    configurer
        .defaultCodecs()
        .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
  }

  @Bean
  @ConditionalOnMissingBean
  public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
    return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
  }

  @Bean
  public WebFilter contextPathWebFilter() {
    log.info("webflux config context path: {}", contextPath);
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      if (request.getURI().getPath().startsWith(contextPath)) {
        return chain.filter(
            exchange
                .mutate()
                .request(request.mutate().contextPath(contextPath).build())
                .build());
      }
      return chain.filter(exchange);
    };
  }
}
