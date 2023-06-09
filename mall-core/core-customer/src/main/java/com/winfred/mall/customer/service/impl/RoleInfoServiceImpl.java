package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.RoleInfoEntity;
import com.winfred.mall.customer.mapper.RoleInfoMapper;
import com.winfred.mall.customer.service.IRoleInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfoEntity> implements IRoleInfoService {

  @Override
  public <T extends BasePageRequest> List<RoleInfoEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(RoleInfoEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends RoleInfoEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<RoleInfoEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(RoleInfoEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

