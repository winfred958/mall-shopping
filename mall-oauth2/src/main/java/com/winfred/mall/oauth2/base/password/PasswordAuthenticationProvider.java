package com.winfred.mall.oauth2.base.password;

import com.winfred.mall.oauth2.base.AbstractAuth2BaseAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author winfred958
 */
@Slf4j
public class PasswordAuthenticationProvider extends AbstractAuth2BaseAuthenticationProvider<PasswordAuthenticationToken> {

  @Override
  public boolean supports(final Class<?> authentication) {
    boolean assignableFrom = PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    log.debug("supports authentication={} assignableFrom:{} ", authentication, assignableFrom);
    return assignableFrom;
  }
}
