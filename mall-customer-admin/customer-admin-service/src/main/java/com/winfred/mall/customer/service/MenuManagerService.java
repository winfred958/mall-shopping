package com.winfred.mall.customer.service;

import com.winfred.mall.customer.entity.response.MenuResponse;

public interface MenuManagerService {

  MenuResponse getMenuResponse(String token);
}
