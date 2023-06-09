package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserAddressEntity;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IUserAddressService extends IService<UserAddressEntity> {

  <T extends BasePageRequest> List<UserAddressEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserAddressEntity entity);

  Integer bulkUpsert(List<? extends UserAddressEntity> list);

  List<UserAddressEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(UserAddressEntity entity);
}

