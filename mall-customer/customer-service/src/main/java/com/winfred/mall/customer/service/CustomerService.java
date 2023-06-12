package com.winfred.mall.customer.service;

import com.winfred.mall.security.entity.MallUser;

/**
 * @author winfred958
 */
public interface CustomerService {

  MallUser getUserDetails(String username);

}
