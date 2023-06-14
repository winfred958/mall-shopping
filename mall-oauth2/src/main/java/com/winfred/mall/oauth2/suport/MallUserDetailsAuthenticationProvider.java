package com.winfred.mall.oauth2.suport;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author winfred958
 */
public class MallUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Override
  protected void additionalAuthenticationChecks(final UserDetails userDetails, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

  }

  @Override
  protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    String name = authentication.getName();
    return null;
  }
}
