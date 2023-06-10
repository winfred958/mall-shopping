package com.winfred.mall.oauth2.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kevin
 */
@RestController
@Slf4j
public class TokenEndpointImpl implements TokenEndpoint {

  @Override
  public ModelAndView require(ModelAndView modelAndView, String error) {
    return null;
  }
}
