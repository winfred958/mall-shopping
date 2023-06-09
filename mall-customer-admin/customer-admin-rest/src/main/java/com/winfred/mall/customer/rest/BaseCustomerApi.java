package com.winfred.mall.customer.rest;

import com.winfred.common.entity.BaseResponse;
import com.winfred.mall.customer.entity.request.UserLoginRequest;
import com.winfred.mall.customer.entity.request.UserRegisterRequest;
import com.winfred.mall.customer.entity.response.UserTokenResponse;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface BaseCustomerApi {

  @GetMapping(value = "/guestToken", produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<BaseResponse<UserTokenResponse>> guest(ServerHttpRequest request);

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<BaseResponse<UserTokenResponse>> login(ServerHttpRequest request, @RequestBody UserLoginRequest loginRequest);

  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<BaseResponse<UserTokenResponse>> register(ServerHttpRequest request, @RequestBody UserRegisterRequest registerRequest);
}
