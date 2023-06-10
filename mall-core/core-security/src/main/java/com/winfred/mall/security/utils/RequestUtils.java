package com.winfred.mall.security.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author winfred958
 */
public class RequestUtils {

  private RequestUtils() {
  }

  public static String getTokenStr(ServerHttpRequest request, String attr) {
    HttpHeaders headers = request.getHeaders();
    return String.valueOf(getHeaderAttr(headers, attr));
  }

  public static String getTokenStr(org.springframework.http.server.ServerHttpRequest request, String attr) {
    HttpHeaders headers = request.getHeaders();
    return String.valueOf(getHeaderAttr(headers, attr));
  }

  private static Object getHeaderAttr(HttpHeaders headers, String attrName) {
    return headers.get(attrName);
  }
}
