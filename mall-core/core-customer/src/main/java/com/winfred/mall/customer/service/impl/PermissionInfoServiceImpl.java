package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.PermissionInfoEntity;
import com.winfred.mall.customer.mapper.PermissionInfoMapper;
import com.winfred.mall.customer.service.IPermissionInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限信息 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfoEntity> implements IPermissionInfoService {

  @Override
  public <T extends BasePageRequest> List<PermissionInfoEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(PermissionInfoEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends PermissionInfoEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<PermissionInfoEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(PermissionInfoEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

