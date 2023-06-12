package com.winfred.mall.customer.repository;

import com.winfred.mall.customer.entity.UserInfoEntity;
import com.winfred.mall.security.entity.MallUser;

/**
 * @author winfred958
 */
public interface CustomerRepository {

  MallUser getUserDetails(String username);

  void saveUserInfo(UserInfoEntity userInfoEntity);
}
