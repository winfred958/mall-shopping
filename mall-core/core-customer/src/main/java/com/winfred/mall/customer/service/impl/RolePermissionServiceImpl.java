package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.RolePermissionEntity;
import com.winfred.mall.customer.mapper.RolePermissionMapper;
import com.winfred.mall.customer.service.IRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色&权限关系 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements IRolePermissionService {

  @Override
  public <T extends BasePageRequest> List<RolePermissionEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(RolePermissionEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends RolePermissionEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<RolePermissionEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(RolePermissionEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

