package com.winfred.mall.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2AuthorizationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* <p>
    * token信息 存储 Mapper 接口
    * </p>
*
* @author winfred
* @since 2023-06-14T10:31:00
*/
    @Mapper
public interface Oauth2AuthorizationMapper extends BaseMapper<Oauth2AuthorizationEntity> {

    <T extends BasePageRequest> List<Oauth2AuthorizationEntity> queryList(T request);

    <T extends BasePageRequest> Integer queryListCount(T request);

    Integer addOne(Oauth2AuthorizationEntity entity);

    Integer bulkUpsert(@Param(value = "items") List<? extends Oauth2AuthorizationEntity> list);

    List<Oauth2AuthorizationEntity> queryByIds(@Param(value = "items") List<?> list);

    Integer updateIfNotNullById(Oauth2AuthorizationEntity entity);
}
