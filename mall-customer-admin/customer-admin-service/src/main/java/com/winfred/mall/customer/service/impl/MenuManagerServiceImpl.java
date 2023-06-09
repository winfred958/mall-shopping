package com.winfred.mall.customer.service.impl;

import com.winfred.mall.customer.entity.response.MenuResponse;
import com.winfred.mall.customer.service.MenuManagerService;
import com.winfred.mall.customer.entity.MenuEntity;
import com.winfred.mall.customer.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuManagerServiceImpl implements MenuManagerService {

  private final IMenuService menuService;

  @Autowired
  public MenuManagerServiceImpl(IMenuService menuService) {
    this.menuService = menuService;
  }

  @Override
  public MenuResponse getMenuResponse(String token) {
    final List<MenuEntity> menuEntityList = menuService.list();
    return buildMenuResponse(menuEntityList);
  }

  /**
   * 两层目录结构
   *
   * @param menuEntityList
   * @return
   */
  private MenuResponse buildMenuResponse(List<MenuEntity> menuEntityList) {
    final MenuResponse response = new MenuResponse();

    return response;
  }
}
