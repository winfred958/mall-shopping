package com.winfred.mall.oauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2RegisteredClientEntity;
import com.winfred.mall.oauth2.mapper.Oauth2RegisteredClientMapper;
import com.winfred.mall.oauth2.service.IOauth2RegisteredClientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * client 注册信息, 用来校验client合法性 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
@Service
public class Oauth2RegisteredClientServiceImpl extends ServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClientEntity> implements IOauth2RegisteredClientService {

  @Override
  public <T extends BasePageRequest> List<Oauth2RegisteredClientEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(Oauth2RegisteredClientEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends Oauth2RegisteredClientEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<Oauth2RegisteredClientEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(Oauth2RegisteredClientEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

