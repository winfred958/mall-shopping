package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.PermissionInfoEntity;

import java.util.List;

/**
 * <p>
 * 权限信息 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IPermissionInfoService extends IService<PermissionInfoEntity> {

  <T extends BasePageRequest> List<PermissionInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(PermissionInfoEntity entity);

  Integer bulkUpsert(List<? extends PermissionInfoEntity> list);

  List<PermissionInfoEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(PermissionInfoEntity entity);
}

