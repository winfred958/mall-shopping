package com.winfred.mall.customer.rest;

import com.winfred.common.constant.MallConstant;
import com.winfred.common.entity.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * @author winfred958
 */
@RequestMapping(value = "/menu")
public interface MenuManagementApi {

  @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<BaseResponse<?>> getMenuList(@RequestHeader(value = MallConstant.TOKEN_KEY) String token);
}
