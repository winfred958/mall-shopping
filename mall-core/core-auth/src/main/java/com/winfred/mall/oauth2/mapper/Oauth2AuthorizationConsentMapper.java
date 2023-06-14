package com.winfred.mall.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2AuthorizationConsentEntity;
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
public interface Oauth2AuthorizationConsentMapper extends BaseMapper<Oauth2AuthorizationConsentEntity> {

    <T extends BasePageRequest> List<Oauth2AuthorizationConsentEntity> queryList(T request);

    <T extends BasePageRequest> Integer queryListCount(T request);

    Integer addOne(Oauth2AuthorizationConsentEntity entity);

    Integer bulkUpsert(@Param(value = "items") List<? extends Oauth2AuthorizationConsentEntity> list);

    List<Oauth2AuthorizationConsentEntity> queryByIds(@Param(value = "items") List<?> list);

    Integer updateIfNotNullById(Oauth2AuthorizationConsentEntity entity);
}
