package com.winfred.mall.security.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author winfred958
 */

@Builder
public class MallUser extends User implements OAuth2AuthenticatedPrincipal, Serializable {

  private static final long serialVersionUID = 6122327769250328426L;

  private final Map<String, Object> attributes = new HashMap<>();

  @Getter
  @Setter
  private Long userId;

  @Getter
  @Setter
  private Long groupId;

  @Getter
  @Setter
  private String displayName;

  public MallUser(String username, String password, boolean enabled, boolean accountNonExpired,
                  boolean credentialsNonExpired, boolean accountNonLocked,
                  Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }

  public MallUser(UserDetails user) {
    super(user.getUsername(), user.getUsername(), user.isEnabled(), user.isAccountNonExpired(),
        user.isCredentialsNonExpired(), user.isAccountNonLocked(),
        user.getAuthorities());
  }

  public MallUser addAttribute(String key, Object value) {
    this.attributes.put(key, value);
    return this;
  }

  public MallUser addAttribute(Map<String, Object> attributes) {
    this.attributes.putAll(attributes);
    return this;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  @Override
  public String getName() {
    return getUsername();
  }


  public static MallUser withUser(UserDetails user) {
    return new MallUser(user);

  }
}
