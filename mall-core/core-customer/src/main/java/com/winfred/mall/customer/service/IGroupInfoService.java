package com.winfred.mall.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.GroupInfoEntity;

import java.util.List;

/**
 * <p>
 * 用户组信息 服务类
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
public interface IGroupInfoService extends IService<GroupInfoEntity> {

  <T extends BasePageRequest> List<GroupInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(GroupInfoEntity entity);

  Integer bulkUpsert(List<? extends GroupInfoEntity> list);

  List<GroupInfoEntity> queryByIds(List<?> list);

  Integer updateIfNotNullById(GroupInfoEntity entity);
}

