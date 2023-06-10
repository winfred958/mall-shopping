package com.winfred.mall.customer.rest;

import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.annotation.security.PermitAll;

/**
 * @author winfred958
 */
@RequestMapping(value = "/user")
public interface CustomerApi {

  @PermitAll()
  @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<UserDetails> getUserDetails(String username);
}
