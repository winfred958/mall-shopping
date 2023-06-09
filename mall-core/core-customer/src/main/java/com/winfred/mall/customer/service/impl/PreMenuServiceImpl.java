package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.PreMenuEntity;
import com.winfred.mall.customer.mapper.PreMenuMapper;
import com.winfred.mall.customer.service.IPreMenuService;
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
public class PreMenuServiceImpl extends ServiceImpl<PreMenuMapper, PreMenuEntity> implements IPreMenuService {

  @Override
  public <T extends BasePageRequest> List<PreMenuEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(PreMenuEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends PreMenuEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<PreMenuEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(PreMenuEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

