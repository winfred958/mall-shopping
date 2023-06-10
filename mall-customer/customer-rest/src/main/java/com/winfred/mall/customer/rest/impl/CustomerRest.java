package com.winfred.mall.customer.rest.impl;

import com.winfred.mall.customer.rest.CustomerApi;
import com.winfred.mall.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.security.PermitAll;


/**
 * @author winfred958
 */
@RestController
public class CustomerRest implements CustomerApi {

  private final CustomerService customerService;

  @Autowired
  public CustomerRest(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public Mono<UserDetails> getUserDetails(String username) {
    UserDetails userDetails = customerService.getUserDetails(username);
    return Mono.just(userDetails);
  }
}
