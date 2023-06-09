package com.winfred.mall.customer.service.imp;

import com.winfred.mall.customer.entity.RoleInfoEntity;
import com.winfred.mall.customer.service.CustomerService;
import com.winfred.mall.customer.service.MallUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author winfred958
 */
@Component
@Slf4j
public class MallUserDetailsServiceImpl implements MallUserDetailsService {

  private final CustomerService customerService;

  @Autowired
  public MallUserDetailsServiceImpl(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    UserDetails userDetails = customerService.getUserDetails(username);
    return Mono.just(userDetails);
  }

  @Override
  public Collection<RoleInfoEntity> getUserRoles(Long userId) {
    return new ArrayList<>();
  }
}
