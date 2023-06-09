package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.RoleInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface RoleInfoMapper extends BaseMapper<RoleInfoEntity> {

  <T extends BasePageRequest> List<RoleInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(RoleInfoEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends RoleInfoEntity> list);

  List<RoleInfoEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(RoleInfoEntity entity);
}
