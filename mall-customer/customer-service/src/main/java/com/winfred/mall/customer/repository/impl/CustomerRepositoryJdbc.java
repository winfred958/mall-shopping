package com.winfred.mall.customer.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.winfred.mall.customer.entity.RoleInfoEntity;
import com.winfred.mall.customer.entity.UserInfoEntity;
import com.winfred.mall.customer.repository.CustomerRepository;
import com.winfred.mall.customer.service.IUserInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author winfred958
 */
public class CustomerRepositoryJdbc implements CustomerRepository {

  private final ApplicationContext applicationContext;

  public CustomerRepositoryJdbc(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public UserDetails getUserDetails(String username) {
    IUserInfoService userInfoService = applicationContext.getBean(IUserInfoService.class);
    QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .eq(UserInfoEntity.USER_NAME, username)
        .last("LIMIT 1");
    UserInfoEntity userInfo = userInfoService.getOne(queryWrapper);
    if (null == userInfo) {
      User.UserBuilder userBuilder = User.withUsername(username)
          .username(username)
          .password("")
          .accountExpired(true)
          .accountLocked(true)
          .authorities(new ArrayList<>())
          .disabled(true);
      return userBuilder.build();
    }
    Long userId = userInfo.getId();
    Collection<RoleInfoEntity> userRoles = getUserRoles(userId);
    User.UserBuilder userBuilder = User.withUsername(username)
        .password(userInfo.getPassword())
        .accountExpired(false)
        .accountLocked(false)
        .disabled(false);
    String[] roles = userRoles
        .stream()
        .map(RoleInfoEntity::getRoleName)
        .toArray(String[]::new);
    userBuilder.roles(roles);
    return userBuilder.build();
  }

  private Collection<RoleInfoEntity> getUserRoles(Long userId) {
    return new ArrayList<>();
  }

  @Override
  public void saveUserInfo(UserInfoEntity userInfoEntity) {

  }
}
