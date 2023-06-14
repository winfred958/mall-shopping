package com.winfred.mall.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2RegisteredClientEntity;

import java.util.List;

/**
 * <p>
 * client 注册信息, 用来校验client合法性 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
public interface IOauth2RegisteredClientService extends IService<Oauth2RegisteredClientEntity> {

  <T extends BasePageRequest> List<Oauth2RegisteredClientEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(Oauth2RegisteredClientEntity entity);

  Integer bulkUpsert(List<? extends Oauth2RegisteredClientEntity> list);

  List<Oauth2RegisteredClientEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(Oauth2RegisteredClientEntity entity);
}

