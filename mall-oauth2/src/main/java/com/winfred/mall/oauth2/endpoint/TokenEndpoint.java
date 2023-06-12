package com.winfred.mall.oauth2.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kevin
 */
@RequestMapping(value = "/token")
public interface TokenEndpoint {

  /**
   * 登录页
   *
   * @param modelAndView
   * @param error
   * @return
   */
  @GetMapping("/login")
  ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error);


}
