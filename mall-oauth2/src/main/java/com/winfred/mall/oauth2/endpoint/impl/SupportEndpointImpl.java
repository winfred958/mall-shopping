package com.winfred.mall.oauth2.endpoint.impl;

import com.alibaba.fastjson2.JSON;
import com.winfred.mall.oauth2.endpoint.SupportEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author winfred958
 */
@Slf4j
@RestController
public class SupportEndpointImpl implements SupportEndpoint {

  @Override
  public ModelAndView login(ModelAndView modelAndView, @RequestParam(required = false) String error) {
    modelAndView.setViewName("ftl/login");
    modelAndView.addObject("error", error);
    return modelAndView;
  }


  @Override
  public void loginForm(final HttpRequest request, final HttpServletResponse response, final Object data) {
    log.info("=========== data: {}", JSON.toJSONString(data));
    try {
      response.sendRedirect("www.baidu.com");
    } catch (IOException e) {
      log.error("", e);
    }
  }
}
