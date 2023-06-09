package com.winfred.common.utils;


public class JwtCommonUtils {

  /**
   * 签名重写
   *
   * @param token
   * @return
   */
  public static String signatureRewrite(String token) {

    return token;
  }

  /**
   * token validation
   *
   * @param token
   * @return
   */
  public static boolean verifyToken(String token) {
    // 签名校验
    // 过期校验
    return true;
  }
}
