package com.winfred.mall.customer.rest.impl;

import com.winfred.common.entity.BaseResponse;
import com.winfred.mall.customer.entity.response.MenuResponse;
import com.winfred.mall.customer.rest.MenuManagementApi;
import com.winfred.mall.customer.service.MenuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MenuManagementRest implements MenuManagementApi {

  private final MenuManagerService menuService;

  @Autowired
  public MenuManagementRest(MenuManagerService menuService) {
    this.menuService = menuService;
  }

  @Override
  public Mono<BaseResponse<?>> getMenuList(String token) {
    final MenuResponse response = menuService.getMenuResponse(token);
    // 构造树形结构
    return Mono.just(BaseResponse.success(response));
  }
}
