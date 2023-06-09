package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserInfoEntity;
import com.winfred.mall.customer.mapper.UserInfoMapper;
import com.winfred.mall.customer.service.IUserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements IUserInfoService {

  @Override
  public <T extends BasePageRequest> List<UserInfoEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(UserInfoEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends UserInfoEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<UserInfoEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(UserInfoEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

