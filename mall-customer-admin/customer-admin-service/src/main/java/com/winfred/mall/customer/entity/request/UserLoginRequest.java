package com.winfred.mall.customer.entity.request;

import lombok.Data;

@Data
public class UserLoginRequest {

  private String userName;

  private String email;

  private String password;

  /**
   * userName
   * <p>
   * email
   * <p>
   * wx
   * <p>
   * zfb
   */
  private String loginType;

  private String code;

  private String openId;

  private Long currentTimestamp = System.currentTimeMillis();
}
