package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserThirdPartyEntity;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IUserThirdPartyService extends IService<UserThirdPartyEntity> {

  <T extends BasePageRequest> List<UserThirdPartyEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserThirdPartyEntity entity);

  Integer bulkUpsert(List<? extends UserThirdPartyEntity> list);

  List<UserThirdPartyEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(UserThirdPartyEntity entity);
}

