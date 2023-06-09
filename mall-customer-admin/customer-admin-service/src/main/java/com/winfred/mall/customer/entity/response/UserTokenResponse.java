package com.winfred.mall.customer.entity.response;

import lombok.Data;

@Data
public class UserTokenResponse {
  private String token;
  private String refreshToken;
}
