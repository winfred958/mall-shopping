package com.winfred.mall.oauth2.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author winfred958
 */
@Slf4j
public abstract class AbstractAuth2BaseAuthenticationProvider<T extends AbstractAuth2BaseAuthenticationToken>
    implements AuthenticationProvider {

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    final T baseAuthentication = (T) authentication;


    return null;
  }

}