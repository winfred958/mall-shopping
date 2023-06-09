package com.winfred.mall.customer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(
    scanBasePackages = {
        "com.winfred.mall.customer.config",
        "com.winfred.mall.customer.service",
        "com.winfred.mall.customer.rest",
    },
    exclude = {
        GsonAutoConfiguration.class
    }
)
@EnableWebFlux
public class CustomerAdmin {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(CustomerAdmin.class);
    springApplication.run(args);
  }
}
