package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.GroupInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户组信息 Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface GroupInfoMapper extends BaseMapper<GroupInfoEntity> {

  <T extends BasePageRequest> List<GroupInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(GroupInfoEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends GroupInfoEntity> list);

  List<GroupInfoEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(GroupInfoEntity entity);
}
