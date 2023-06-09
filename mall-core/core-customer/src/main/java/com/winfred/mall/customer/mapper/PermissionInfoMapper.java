package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.PermissionInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限信息 Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface PermissionInfoMapper extends BaseMapper<PermissionInfoEntity> {

  <T extends BasePageRequest> List<PermissionInfoEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(PermissionInfoEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends PermissionInfoEntity> list);

  List<PermissionInfoEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(PermissionInfoEntity entity);
}
