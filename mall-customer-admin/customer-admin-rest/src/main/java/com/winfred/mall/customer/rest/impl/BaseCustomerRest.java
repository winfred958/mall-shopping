package com.winfred.mall.customer.rest.impl;

import cn.hutool.crypto.digest.MD5;
import com.winfred.common.entity.BaseResponse;
import com.winfred.mall.customer.entity.request.UserLoginRequest;
import com.winfred.mall.customer.entity.request.UserRegisterRequest;
import com.winfred.mall.customer.entity.response.UserTokenResponse;
import com.winfred.mall.customer.rest.BaseCustomerApi;
import com.winfred.mall.customer.rest.HttpRequestIpUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class BaseCustomerRest implements BaseCustomerApi {

  @Override
  public Mono<BaseResponse<UserTokenResponse>> guest(ServerHttpRequest request) {
    final HttpHeaders headers = request.getHeaders();
    final List<String> userAgent = headers.get("User-Agent");
    final String ipAddr = HttpRequestIpUtil.getIpAddr(request);
    final UserTokenResponse tokenResponse = new UserTokenResponse();
    final String token = MD5.create().digestHex(ipAddr + "<-_->" + userAgent);
    tokenResponse.setToken(token);
    final BaseResponse<UserTokenResponse> baseResponse = BaseResponse.success(tokenResponse);
    return Mono.just(baseResponse);
  }

  @Override
  public Mono<BaseResponse<UserTokenResponse>> login(ServerHttpRequest request, UserLoginRequest loginRequest) {

    return null;
  }

  @Override
  public Mono<BaseResponse<UserTokenResponse>> register(ServerHttpRequest request, UserRegisterRequest registerRequest) {
    return null;
  }
}
