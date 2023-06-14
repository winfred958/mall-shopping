package com.winfred.mall.oauth2.service.impl;

import com.winfred.mall.oauth2.service.MallUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现内部 sso 用户名密码登录
 *
 * @author winfred958
 */
@Slf4j
@Service
public class MallUserDetailsServiceImpl implements MallUserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO: 从远程, mall-customer 服务获取

    return null;
  }
}
