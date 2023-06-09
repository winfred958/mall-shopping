package com.winfred.mall.customer.entity.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuResponse {

  private List<Menu> menusList;

  public MenuResponse addMenu(Menu menu) {
    if (null == menusList) {
      menusList = new ArrayList<>(10);
    }
    menusList.add(menu);
    return this;
  }

  @Data
  public static class Menu {

    private Long id;

    /**
     * 对应 router-link.to
     */
    private String routerLink;

    private String name;

    private List<Menu> subMenus;
  }
}
