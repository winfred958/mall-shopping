package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.RoleInfoEntity;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IRoleInfoService extends IService<RoleInfoEntity> {

  <T extends BasePageRequest> List<RoleInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(RoleInfoEntity entity);

  Integer bulkUpsert(List<? extends RoleInfoEntity> list);

  List<RoleInfoEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(RoleInfoEntity entity);
}

