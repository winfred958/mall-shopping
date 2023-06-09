package com.winfred.mall.customer.entity.response;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Data
public class UserRoleResponse {

  private Long userId;

  private Set<Role> roles;

  public UserRoleResponse addRole(Role role) {
    if (null == roles) {
      roles = new TreeSet<>(Comparator.comparing(Role::getRoleName));
    }
    roles.add(role);
    return this;
  }

  @Builder
  @Data
  public static class Role {
    private Integer id;

    private String roleName;
  }
}
