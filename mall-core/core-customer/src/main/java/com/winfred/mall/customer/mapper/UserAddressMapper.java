package com.winfred.mall.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.customer.entity.UserAddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddressEntity> {

  <T extends BasePageRequest> List<UserAddressEntity> queryList(T request);

  <T extends BasePageRequest> Integer queryListCount(T request);

  Integer addOne(UserAddressEntity entity);

  Integer bulkUpsert(@Param(value = "items") List<? extends UserAddressEntity> list);

  List<UserAddressEntity> queryByIds(@Param(value = "items") List<?> list);

  Integer updateIfNotNullById(UserAddressEntity entity);
}
