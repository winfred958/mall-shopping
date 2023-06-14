package com.winfred.mall.oauth2.base.password;

import com.winfred.mall.oauth2.base.AbstractAuth2BaseAuthenticationConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * 密码认证 converter
 *
 * @author winfred958
 */
@Slf4j
public class PasswordAuthenticationConverter extends AbstractAuth2BaseAuthenticationConverter<PasswordAuthenticationToken> {

  @Override
  public PasswordAuthenticationToken buildToken(final Authentication authentication,
                                                final Set<String> scopes,
                                                final Map<String, Object> additionalAttrs) {
    return new PasswordAuthenticationToken(AuthorizationGrantType.PASSWORD, authentication, scopes, additionalAttrs);
  }

  @Override
  public boolean support(final HttpServletRequest request) {
    String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
    return AuthorizationGrantType.PASSWORD.getValue().equals(grantType);
  }
}
