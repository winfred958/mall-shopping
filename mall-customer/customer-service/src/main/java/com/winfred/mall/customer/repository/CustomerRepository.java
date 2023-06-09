package com.winfred.mall.customer.repository;

import com.winfred.mall.customer.entity.UserInfoEntity;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author winfred958
 */
public interface CustomerRepository {

  UserDetails getUserDetails(String username);

  void saveUserInfo(UserInfoEntity userInfoEntity);
}
