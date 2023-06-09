package com.winfred.mall.customer.admin.service;

import com.winfred.mall.customer.admin.base.BaseTest;
import com.winfred.mall.customer.entity.response.UserRoleResponse;
import com.winfred.mall.customer.service.base.CustomerAdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Slf4j
public class CustomerAdminServiceTest extends BaseTest {

  @Autowired
  private CustomerAdminService customerAdmin;

  /**
   * https://developers.google.com/profile/badges/events/io/2023/attendee
   */
  @Test
  public void getUserInfo() {
    final UserRoleResponse userRoleResponse = customerAdmin.getUserRole(1L);
    log.info("{}", userRoleResponse.getUserId());
    final Set<UserRoleResponse.Role> roles = userRoleResponse.getRoles();
    if (CollectionUtils.isEmpty(roles)) {
      return;
    }
    roles
        .forEach(role -> {
          log.info("{}: {}", role.getId(), role.getRoleName());
        });
  }
}
