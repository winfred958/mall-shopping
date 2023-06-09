package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserGroupEntity;

import java.util.List;

/**
 * <p>
 * 用户所属组关系 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IUserGroupService extends IService<UserGroupEntity> {

  <T extends BasePageRequest> List<UserGroupEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserGroupEntity entity);

  Integer bulkUpsert(List<? extends UserGroupEntity> list);

  List<UserGroupEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(UserGroupEntity entity);
}

