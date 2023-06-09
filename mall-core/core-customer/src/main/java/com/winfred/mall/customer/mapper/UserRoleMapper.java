package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户与角色关系(user独立分配的role) Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

  <T extends BasePageRequest> List<UserRoleEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserRoleEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends UserRoleEntity> list);

  List<UserRoleEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(UserRoleEntity entity);
}
