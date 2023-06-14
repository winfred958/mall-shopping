package com.winfred.mall.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2AuthorizationEntity;

import java.util.List;

/**
 * <p>
 * token信息 存储 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
public interface IOauth2AuthorizationService extends IService<Oauth2AuthorizationEntity> {

  <T extends BasePageRequest> List<Oauth2AuthorizationEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(Oauth2AuthorizationEntity entity);

  Integer bulkUpsert(List<? extends Oauth2AuthorizationEntity> list);

  List<Oauth2AuthorizationEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(Oauth2AuthorizationEntity entity);
}

