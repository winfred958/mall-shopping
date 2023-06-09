package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserInfoEntity;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IUserInfoService extends IService<UserInfoEntity> {

  <T extends BasePageRequest> List<UserInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserInfoEntity entity);

  Integer bulkUpsert(List<? extends UserInfoEntity> list);

  List<UserInfoEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(UserInfoEntity entity);
}

