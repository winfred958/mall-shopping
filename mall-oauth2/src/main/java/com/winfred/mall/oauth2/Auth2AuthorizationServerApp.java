package com.winfred.mall.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author winfred958
 */
@SpringBootApplication
public class Auth2AuthorizationServerApp {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(Auth2AuthorizationServerApp.class);
    springApplication.run(args);
  }
}
