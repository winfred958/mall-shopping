package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.GroupRoleEntity;

import java.util.List;

/**
 * <p>
 * 用户组与角色关系(group通用role) 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IGroupRoleService extends IService<GroupRoleEntity> {

  <T extends BasePageRequest> List<GroupRoleEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(GroupRoleEntity entity);

  Integer bulkUpsert(List<? extends GroupRoleEntity> list);

  List<GroupRoleEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(GroupRoleEntity entity);
}

