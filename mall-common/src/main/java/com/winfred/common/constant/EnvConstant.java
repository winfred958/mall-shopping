package com.winfred.common.constant;

public enum EnvConstant {
  /**
   * dev
   */
  DEV("dev", "开发环境"),
  /**
   * test
   */
  TEST("test", "测试环境"),
  /**
   * uat
   */
  UAT("uat", "预发布"),
  /**
   * prd
   */
  PROD("prod", "生产环境"),
  ;

  private String name;
  private String describe;

  EnvConstant(String name, String describe) {
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
