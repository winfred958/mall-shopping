package com.winfred.mall.customer.service.base;

import com.winfred.mall.customer.entity.GroupInfoEntity;
import com.winfred.mall.customer.entity.RoleInfoEntity;
import com.winfred.mall.customer.entity.UserInfoEntity;
import com.winfred.mall.customer.entity.response.MenuResponse;
import com.winfred.mall.customer.entity.response.UserRoleResponse;

import java.util.Collection;

public interface CustomerAdminService {

  UserInfoEntity addUser(UserInfoEntity userInfo);

  void addGroup(UserInfoEntity userInfo, GroupInfoEntity groupInfo);

  void addGroup(UserInfoEntity userInfo, Collection<GroupInfoEntity> groups);

  void addGroup(String userId, Collection<String> groupNames);

  void removeGroup(UserInfoEntity userInfo, GroupInfoEntity groupInfo);

  void removeGroup(UserInfoEntity userInfo, Collection<GroupInfoEntity> groups);

  void removeGroup(String userId, Collection<String> groupNames);

  void addRole(UserInfoEntity userInfo, RoleInfoEntity roleInfo);

  void addRole(UserInfoEntity userInfo, Collection<RoleInfoEntity> roles);

  void addRole(String userId, Collection<String> roleNames);

  void removeRole(UserInfoEntity userInfo, RoleInfoEntity roleInfo);

  void removeRole(UserInfoEntity userInfo, Collection<RoleInfoEntity> roles);

  void removeRole(String userId, Collection<String> roleNames);

  /**
   * 获取菜单列表
   *
   * @param userInfo
   * @return
   */
  MenuResponse getMenus(UserInfoEntity userInfo);

  MenuResponse getMenus(Long userId);

  /**
   * 获取用户角色列表
   *
   * @param userInfo
   * @return
   */
  UserRoleResponse getUserRole(UserInfoEntity userInfo);

  UserRoleResponse getUserRole(Long userId);
}
