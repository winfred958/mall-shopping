package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.GroupRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户组与角色关系(group通用role) Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface GroupRoleMapper extends BaseMapper<GroupRoleEntity> {

  <T extends BasePageRequest> List<GroupRoleEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(GroupRoleEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends GroupRoleEntity> list);

  List<GroupRoleEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(GroupRoleEntity entity);
}
