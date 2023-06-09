package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.RolePermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色&权限关系 Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

  <T extends BasePageRequest> List<RolePermissionEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(RolePermissionEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends RolePermissionEntity> list);

  List<RolePermissionEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(RolePermissionEntity entity);
}
