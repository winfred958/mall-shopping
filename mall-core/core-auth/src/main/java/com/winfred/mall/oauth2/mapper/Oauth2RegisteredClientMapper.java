package com.winfred.mall.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winfred.common.entity.BasePageRequest;
import com.winfred.mall.oauth2.entity.Oauth2RegisteredClientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* <p>
    * client 注册信息, 用来校验client合法性 Mapper 接口
    * </p>
*
* @author winfred
* @since 2023-06-14T10:31:00
*/
    @Mapper
public interface Oauth2RegisteredClientMapper extends BaseMapper<Oauth2RegisteredClientEntity> {

    <T extends BasePageRequest> List<Oauth2RegisteredClientEntity> queryList(T request);

    <T extends BasePageRequest> Integer queryListCount(T request);

    Integer addOne(Oauth2RegisteredClientEntity entity);

    Integer bulkUpsert(@Param(value = "items") List<? extends Oauth2RegisteredClientEntity> list);

    List<Oauth2RegisteredClientEntity> queryByIds(@Param(value = "items") List<?> list);

    Integer updateIfNotNullById(Oauth2RegisteredClientEntity entity);
}
