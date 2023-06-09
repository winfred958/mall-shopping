package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.PreMenuEntity;

import java.util.List;

/**
 * <p>
 * 角色&权限关系 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IPreMenuService extends IService<PreMenuEntity> {

  <T extends BasePageRequest> List<PreMenuEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(PreMenuEntity entity);

  Integer bulkUpsert(List<? extends PreMenuEntity> list);

  List<PreMenuEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(PreMenuEntity entity);
}

