package com.winfred.mall.customer.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.winfred.common.constant.MallConstant;
import com.winfred.mall.customer.entity.RoleInfoEntity;
import com.winfred.mall.customer.entity.UserInfoEntity;
import com.winfred.mall.customer.entity.UserRoleEntity;
import com.winfred.mall.customer.repository.CustomerRepository;
import com.winfred.mall.customer.service.IRoleInfoService;
import com.winfred.mall.customer.service.IUserInfoService;
import com.winfred.mall.customer.service.IUserRoleService;
import com.winfred.mall.security.entity.MallUser;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author winfred958
 */
public class CustomerRepositoryJdbc implements CustomerRepository {

  private final ApplicationContext applicationContext;

  public CustomerRepositoryJdbc(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public MallUser getUserDetails(String username) {
    final IUserInfoService userInfoService = applicationContext.getBean(IUserInfoService.class);
    final QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .eq(UserInfoEntity.USER_NAME, username)
        .last("LIMIT 1");
    final UserInfoEntity userInfo = userInfoService.getOne(queryWrapper);
    if (null == userInfo) {
      // 用户不存在
      return buildAnonymousUser(username);
    }
    final Long userId = userInfo.getId();
    final Collection<RoleInfoEntity> userRoles = getUserRoles(userId);
    final User.UserBuilder userBuilder = User.withUsername(username)
        .password(userInfo.getPassword())
        .accountExpired(false)
        .accountLocked(false)
        .disabled(false);
    final String[] roles = userRoles
        .stream()
        .map(RoleInfoEntity::getRoleName)
        .toArray(String[]::new);
    userBuilder.roles(roles);
    MallUser mallUser = MallUser.withUser(userBuilder.build());
    mallUser.setUserId(userId);
    // TODO: mallUser groupId
    return mallUser;
  }

  /**
   * 匿名用户
   *
   * @param username
   * @return
   */
  private MallUser buildAnonymousUser(final String username) {

    List<GrantedAuthority> list = new ArrayList<>();
    list.add(new SimpleGrantedAuthority(MallConstant.ROLE_ANONYMOUS));

    final User.UserBuilder userBuilder = User.withUsername(username)
        .username(username)
        .password("")
        .accountExpired(true)
        .accountLocked(true)
        .authorities(list)
        .disabled(true);
    final UserDetails userDetails = userBuilder.build();
    return MallUser.withUser(userDetails);
  }

  private Collection<RoleInfoEntity> getUserRoles(Long userId) {
    IUserRoleService userRoleService = applicationContext.getBean(IUserRoleService.class);
    QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq(UserRoleEntity.USER_ID, userId);
    List<UserRoleEntity> userRoleEntities = userRoleService.list(queryWrapper);
    Set<Integer> roleIds = userRoleEntities
        .stream()
        .map(UserRoleEntity::getRoleId)
        .collect(Collectors.toSet());
    return getUserRoles(roleIds);
  }

  private Collection<RoleInfoEntity> getUserRoles(final Set<Integer> roleIds) {
    IRoleInfoService roleInfoService = applicationContext.getBean(IRoleInfoService.class);
    QueryWrapper<RoleInfoEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.in(RoleInfoEntity.ID, roleIds);
    return roleInfoService.list(queryWrapper);
  }

  @Override
  public void saveUserInfo(UserInfoEntity userInfoEntity) {

  }
}
