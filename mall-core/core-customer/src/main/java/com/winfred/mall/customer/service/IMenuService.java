package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.MenuEntity;

import java.util.List;

/**
 * <p>
 * 角色&权限关系 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IMenuService extends IService<MenuEntity> {

  <T extends BasePageRequest> List<MenuEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(MenuEntity entity);

  Integer bulkUpsert(List<? extends MenuEntity> list);

  List<MenuEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(MenuEntity entity);
}

