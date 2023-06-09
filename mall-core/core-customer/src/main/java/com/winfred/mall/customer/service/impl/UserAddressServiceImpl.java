package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserAddressEntity;
import com.winfred.mall.customer.mapper.UserAddressMapper;
import com.winfred.mall.customer.service.IUserAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements IUserAddressService {

  @Override
  public <T extends BasePageRequest> List<UserAddressEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(UserAddressEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends UserAddressEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<UserAddressEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(UserAddressEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

