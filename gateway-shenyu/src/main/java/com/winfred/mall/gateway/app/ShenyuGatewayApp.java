package com.winfred.mall.gateway.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(
    exclude = {
        GsonAutoConfiguration.class
    }
)
@EnableWebFlux
@Slf4j
public class ShenyuGatewayApp {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(ShenyuGatewayApp.class);
    springApplication.run(args);
  }
}
