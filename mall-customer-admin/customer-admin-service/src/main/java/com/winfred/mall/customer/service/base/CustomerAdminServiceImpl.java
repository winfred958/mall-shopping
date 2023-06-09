package com.winfred.mall.customer.service.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.winfred.mall.customer.entity.*;
import com.winfred.mall.customer.entity.response.MenuResponse;
import com.winfred.mall.customer.entity.response.UserRoleResponse;
import com.winfred.mall.customer.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerAdminServiceImpl implements CustomerAdminService {

  private final IUserGroupService userGroupService;

  private final IGroupRoleService groupRoleService;

  private final IRoleInfoService roleInfoService;

  private final IUserRoleService userRoleService;

  @Autowired
  private IRolePermissionService rolePermissionService;

  @Autowired
  private IPreMenuService preMenuService;

  @Autowired
  private IMenuService menuService;

  @Autowired
  public CustomerAdminServiceImpl(IUserGroupService userGroupService, IGroupRoleService groupRoleService, IRoleInfoService roleInfoService, IUserRoleService userRoleService) {
    this.userGroupService = userGroupService;
    this.groupRoleService = groupRoleService;
    this.roleInfoService = roleInfoService;
    this.userRoleService = userRoleService;
  }

  @Override
  public UserInfoEntity addUser(UserInfoEntity userInfo) {
    return null;
  }

  @Override
  public void addGroup(UserInfoEntity userInfo, GroupInfoEntity groupInfo) {

  }

  @Override
  public void addGroup(UserInfoEntity userInfo, Collection<GroupInfoEntity> groups) {

  }

  @Override
  public void removeGroup(UserInfoEntity userInfo, GroupInfoEntity groupInfo) {

  }

  @Override
  public void removeGroup(UserInfoEntity userInfo, Collection<GroupInfoEntity> groups) {

  }

  @Override
  public void addRole(UserInfoEntity userInfo, RoleInfoEntity roleInfo) {

  }

  @Override
  public void addRole(UserInfoEntity userInfo, Collection<RoleInfoEntity> roles) {

  }

  @Override
  public void removeRole(UserInfoEntity userInfo, RoleInfoEntity roleInfo) {

  }

  @Override
  public void removeRole(UserInfoEntity userInfo, Collection<RoleInfoEntity> roles) {

  }

  @Override
  public void addGroup(String userId, Collection<String> groupNames) {

  }

  @Override
  public void removeGroup(String userId, Collection<String> groupNames) {

  }

  @Override
  public void addRole(String userId, Collection<String> roleNames) {

  }

  @Override
  public void removeRole(String userId, Collection<String> roleNames) {

  }

  @Override
  public MenuResponse getMenus(UserInfoEntity userInfo) {
    final Long userId = userInfo.getId();
    return getMenus(userId);
  }

  @Override
  public MenuResponse getMenus(Long userId) {
    final UserRoleResponse roleResponse = getUserRole(userId);
    final Set<UserRoleResponse.Role> roleSet = roleResponse.getRoles();
    final List<MenuEntity> menuList = getMenuList(roleSet);

    return null;
  }

  private List<MenuEntity> getMenuList(Set<UserRoleResponse.Role> roleSet) {
    QueryWrapper<RolePermissionEntity> rolePermissionEntityQueryWrapper = new QueryWrapper<>();
    rolePermissionEntityQueryWrapper
        .in(RolePermissionEntity.ROLE_ID,
            roleSet
                .stream()
                .map(UserRoleResponse.Role::getId).collect(Collectors.toList())
        );
    final List<RolePermissionEntity> rolePermissionEntities = rolePermissionService.list(rolePermissionEntityQueryWrapper);
    if (CollectionUtils.isEmpty(rolePermissionEntities)) {
      return new ArrayList<>();
    }
    final Set<Integer> perIdSet = rolePermissionEntities
        .stream()
        .map(RolePermissionEntity::getPermissionId)
        .collect(Collectors.toSet());
    QueryWrapper<PreMenuEntity> preMenuEntityQueryWrapper = new QueryWrapper<>();
    preMenuEntityQueryWrapper
        .in(PreMenuEntity.PERMISSION_ID, perIdSet);
    final List<PreMenuEntity> preMenuEntities = preMenuService
        .list(preMenuEntityQueryWrapper);
    if (CollectionUtils.isEmpty(preMenuEntities)) {
      return new ArrayList<>();
    }
    final Set<Integer> menuIdSet = preMenuEntities
        .stream()
        .map(PreMenuEntity::getMenuId)
        .collect(Collectors.toSet());

    QueryWrapper<MenuEntity> menuEntityQueryWrapper = new QueryWrapper<>();
    menuEntityQueryWrapper
        .in(MenuEntity.ID, menuIdSet);
    return menuService
        .list(menuEntityQueryWrapper);
  }

  private List<RoleInfoEntity> getUserRoleDirect(Long userId) {
    QueryWrapper<UserRoleEntity> userGroupEntityQueryWrapper = new QueryWrapper<>();
    userGroupEntityQueryWrapper
        .eq(UserRoleEntity.USER_ID, userId);
    final List<UserRoleEntity> userRoleEntities = userRoleService
        .list(userGroupEntityQueryWrapper);
    if (CollectionUtils.isEmpty(userRoleEntities)) {
      return new ArrayList<>();
    }
    final Set<Integer> roleIdSet = userRoleEntities
        .stream()
        .map(UserRoleEntity::getRoleId)
        .collect(Collectors.toSet());
    return getRoleInfoEntities(roleIdSet);
  }

  private List<RoleInfoEntity> getUserRoleFromGroup(Long userId) {
    QueryWrapper<UserGroupEntity> userGroupEntityQueryWrapper = new QueryWrapper<>();
    userGroupEntityQueryWrapper
        .eq(UserGroupEntity.USER_ID, userId);
    final List<UserGroupEntity> userGroupEntities = userGroupService.list(userGroupEntityQueryWrapper);
    if (CollectionUtils.isEmpty(userGroupEntities)) {
      return new ArrayList<>();
    }
    final Set<Integer> groupIdSet = userGroupEntities
        .stream()
        .map(UserGroupEntity::getGroupId)
        .collect(Collectors.toSet());

    QueryWrapper<GroupRoleEntity> groupRoleEntityQueryWrapper = new QueryWrapper<>();
    groupRoleEntityQueryWrapper
        .in(GroupRoleEntity.GROUP_ID, groupIdSet);
    final List<GroupRoleEntity> groupRoleEntities = groupRoleService
        .list(groupRoleEntityQueryWrapper);
    if (CollectionUtils.isEmpty(groupRoleEntities)) {
      return new ArrayList<>();
    }
    final Set<Integer> roleIdSet = groupRoleEntities
        .stream()
        .map(GroupRoleEntity::getRoleId)
        .collect(Collectors.toSet());

    return getRoleInfoEntities(roleIdSet);
  }

  private List<RoleInfoEntity> getRoleInfoEntities(Set<Integer> roleIdSet) {
    QueryWrapper<RoleInfoEntity> roleEntityQueryWrapper = new QueryWrapper<>();
    roleEntityQueryWrapper
        .in(RoleInfoEntity.ID, roleIdSet);
    final List<RoleInfoEntity> roleInfoEntities = roleInfoService.list(roleEntityQueryWrapper);
    if (CollectionUtils.isEmpty(roleInfoEntities)) {
      return new ArrayList<>();
    }
    return roleInfoEntities;
  }

  @Override
  public UserRoleResponse getUserRole(UserInfoEntity userInfo) {
    final Long userId = userInfo.getId();
    return getUserRole(userId);
  }

  @Override
  public UserRoleResponse getUserRole(Long userId) {
    final UserRoleResponse roleResponse = new UserRoleResponse();
    roleResponse.setUserId(userId);
    final List<RoleInfoEntity> roleInfoEntities = getUserRoleFromGroup(userId);
    final List<RoleInfoEntity> roleInfoEntities2 = getUserRoleDirect(userId);
    add2RoleResponse(roleResponse, roleInfoEntities);
    add2RoleResponse(roleResponse, roleInfoEntities2);
    return roleResponse;
  }

  private void add2RoleResponse(UserRoleResponse roleResponse, List<RoleInfoEntity> roleInfoEntities) {
    if (CollectionUtils.isEmpty(roleInfoEntities)) {
      return;
    }
    roleInfoEntities
        .forEach(roleInfo -> {
          final UserRoleResponse.Role role = UserRoleResponse
              .Role.builder()
              .id(roleInfo.getId())
              .roleName(roleInfo.getRoleName())
              .build();
          roleResponse.addRole(role);
        });
  }
}

