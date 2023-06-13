package com.winfred.common.constant;

/**
 * @author winfred958
 */
@Deprecated
public enum JWTConstant {
  /**
   * userId
   */
  USER_ID("userId", "用户id"),
  /**
   * nickname
   */
  NICKNAME("nickname", "昵称"),
  /**
   * roles
   */
  ROLES("roles", "角色列表"),
  /**
   * authenticated
   */
  AUTHENTICATED("authenticated", "认证标识"),
  ;

  private String name;
  private String describe;

  JWTConstant(String name, String describe) {
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
