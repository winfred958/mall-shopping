package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserRoleEntity;

import java.util.List;

/**
 * <p>
 * 用户与角色关系(user独立分配的role) 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IUserRoleService extends IService<UserRoleEntity> {

  <T extends BasePageRequest> List<UserRoleEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserRoleEntity entity);

  Integer bulkUpsert(List<? extends UserRoleEntity> list);

  List<UserRoleEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(UserRoleEntity entity);
}

