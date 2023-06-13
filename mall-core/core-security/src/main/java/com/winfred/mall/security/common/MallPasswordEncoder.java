package com.winfred.mall.security.common;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author winfred958
 */
public class MallPasswordEncoder implements PasswordEncoder {

  @Override
  public String encode(final CharSequence rawPassword) {


    return null;
  }

  @Override
  public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
    return false;
  }
}
