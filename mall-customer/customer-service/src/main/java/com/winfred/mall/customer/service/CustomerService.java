package com.winfred.mall.customer.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author winfred958
 */
public interface CustomerService {

  UserDetails getUserDetails(String username);

}
