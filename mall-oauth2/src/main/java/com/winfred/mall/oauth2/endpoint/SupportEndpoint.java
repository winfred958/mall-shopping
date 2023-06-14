package com.winfred.mall.oauth2.endpoint;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @author winfred958
 */
@RequestMapping(value = "/support")
public interface SupportEndpoint {

  /**
   * 登录页
   *
   * @param modelAndView
   * @param error
   * @return
   */
  @GetMapping(value = "/login")
  ModelAndView login(ModelAndView modelAndView, @RequestParam(required = false) String error);


  /**
   * 登录
   *
   * @param request
   * @param response
   * @param data
   * @return
   */
  @PostMapping(value = "/login/form")
  void loginForm(HttpRequest request, HttpServletResponse response, @RequestBody Object data);

}
