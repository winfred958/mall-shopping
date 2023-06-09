package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.PreMenuEntity;
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
public interface PreMenuMapper extends BaseMapper<PreMenuEntity> {

  <T extends BasePageRequest> List<PreMenuEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(PreMenuEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends PreMenuEntity> list);

  List<PreMenuEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(PreMenuEntity entity);
}
