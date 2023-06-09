package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserRoleEntity;
import com.winfred.mall.customer.mapper.UserRoleMapper;
import com.winfred.mall.customer.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户与角色关系(user独立分配的role) 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements IUserRoleService {

  @Override
  public <T extends BasePageRequest> List<UserRoleEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(UserRoleEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends UserRoleEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<UserRoleEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(UserRoleEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

