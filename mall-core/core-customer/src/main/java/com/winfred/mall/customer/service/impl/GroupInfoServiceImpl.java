package com.winfred.mall.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.GroupInfoEntity;
import com.winfred.mall.customer.mapper.GroupInfoMapper;
import com.winfred.mall.customer.service.IGroupInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户组信息 服务实现类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Service
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper, GroupInfoEntity> implements IGroupInfoService {

  @Override
  public <T extends BasePageRequest> List<GroupInfoEntity> queryList(T request) {
    return getBaseMapper().queryList(request);
  }

  @Override
  public <T extends BasePageRequest> Integer queryListCount(T request) {
    return getBaseMapper().queryListCount(request);
  }

  @Override
  public Integer addOne(GroupInfoEntity entity) {
    return getBaseMapper().addOne(entity);
  }

  @Override
  public Integer bulkUpsert(List<? extends GroupInfoEntity> list) {
    return getBaseMapper().bulkUpsert(list);
  }

  @Override
  public List<GroupInfoEntity> queryByIds(List<?> list) {
    return getBaseMapper().queryByIds(list);
  }

  @Override
  public Integer updateIfNotNullById(GroupInfoEntity entity) {
    return getBaseMapper().updateIfNotNullById(entity);
  }

}

