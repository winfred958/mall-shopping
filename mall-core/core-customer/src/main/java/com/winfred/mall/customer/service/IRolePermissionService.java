package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.RolePermissionEntity;

import java.util.List;

/**
 * <p>
 * 角色&权限关系 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IRolePermissionService extends IService<RolePermissionEntity> {

  <T extends BasePageRequest> List<RolePermissionEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(RolePermissionEntity entity);

  Integer bulkUpsert(List<? extends RolePermissionEntity> list);

  List<RolePermissionEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(RolePermissionEntity entity);
}

