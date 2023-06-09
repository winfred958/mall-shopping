package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户所属组关系 Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroupEntity> {

  <T extends BasePageRequest> List<UserGroupEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserGroupEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends UserGroupEntity> list);

  List<UserGroupEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(UserGroupEntity entity);
}
