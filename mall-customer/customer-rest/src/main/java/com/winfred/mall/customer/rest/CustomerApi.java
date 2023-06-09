package com.winfred.mall.customer.rest;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * @author winfred958
 */
@RequestMapping(value = "/user")
public interface CustomerApi {

  @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<UserDetails> getUserDetails(String username);
}
