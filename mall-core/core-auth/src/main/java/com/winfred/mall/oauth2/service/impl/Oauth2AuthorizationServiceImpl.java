package com.winfred.mall.oauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2AuthorizationEntity;
import com.winfred.mall.oauth2.mapper.Oauth2AuthorizationMapper;
import com.winfred.mall.oauth2.service.IOauth2AuthorizationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * token信息 存储 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
@Service
public class Oauth2AuthorizationServiceImpl extends ServiceImpl<Oauth2AuthorizationMapper, Oauth2AuthorizationEntity> implements IOauth2AuthorizationService {

  @Override
  public <T extends BasePageRequest> List<Oauth2AuthorizationEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(Oauth2AuthorizationEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends Oauth2AuthorizationEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<Oauth2AuthorizationEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(Oauth2AuthorizationEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

