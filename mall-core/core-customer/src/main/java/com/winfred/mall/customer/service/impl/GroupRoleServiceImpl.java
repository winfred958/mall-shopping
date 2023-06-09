package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.GroupRoleEntity;
import com.winfred.mall.customer.mapper.GroupRoleMapper;
import com.winfred.mall.customer.service.IGroupRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户组与角色关系(group通用role) 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class GroupRoleServiceImpl extends ServiceImpl<GroupRoleMapper, GroupRoleEntity> implements IGroupRoleService {

  @Override
  public <T extends BasePageRequest> List<GroupRoleEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(GroupRoleEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends GroupRoleEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<GroupRoleEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(GroupRoleEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

