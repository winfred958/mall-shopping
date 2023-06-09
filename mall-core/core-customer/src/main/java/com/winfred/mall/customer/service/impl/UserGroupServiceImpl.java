package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserGroupEntity;
import com.winfred.mall.customer.mapper.UserGroupMapper;
import com.winfred.mall.customer.service.IUserGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户所属组关系 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroupEntity> implements IUserGroupService {

  @Override
  public <T extends BasePageRequest> List<UserGroupEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(UserGroupEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends UserGroupEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<UserGroupEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(UserGroupEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

