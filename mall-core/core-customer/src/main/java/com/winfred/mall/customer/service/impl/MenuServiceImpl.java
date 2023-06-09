package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.MenuEntity;
import com.winfred.mall.customer.mapper.MenuMapper;
import com.winfred.mall.customer.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService {

  @Override
  public <T extends BasePageRequest> List<MenuEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(MenuEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends MenuEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<MenuEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(MenuEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

