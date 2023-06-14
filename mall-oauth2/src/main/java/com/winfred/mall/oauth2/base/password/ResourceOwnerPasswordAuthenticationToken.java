package com.winfred.mall.oauth2.base.password;

import com.winfred.mall.oauth2.base.AbstractResourceOwnerAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import javax.security.auth.Subject;
import java.util.Map;
import java.util.Set;

/**
 * @author winfred958
 */
public class ResourceOwnerPasswordAuthenticationToken extends AbstractResourceOwnerAuthenticationToken {

  private static final long serialVersionUID = 2433635356279267255L;

  public ResourceOwnerPasswordAuthenticationToken(final AuthorizationGrantType authorizationGrantType, final Authentication authentication, final Set<String> scopes, final Map<String, Object> additionalAttrs) {
    super(authorizationGrantType, authentication, scopes, additionalAttrs);
  }

  @Override
  public boolean implies(final Subject subject) {
    return super.implies(subject);
  }
}
