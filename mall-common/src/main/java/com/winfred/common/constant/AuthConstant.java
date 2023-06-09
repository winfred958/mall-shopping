package com.winfred.common.constant;

public enum AuthConstant {
  /**
   * token name
   */
  TOKEN_NAME("user_token", "token名称"),

  ;

  private String name;
  private String describe;

  AuthConstant(String name, String describe) {
    this.name = name;
    this.describe = describe;
  }

  public String getName() {
    return name;
  }

  public String getDescribe() {
    return describe;
  }
}
