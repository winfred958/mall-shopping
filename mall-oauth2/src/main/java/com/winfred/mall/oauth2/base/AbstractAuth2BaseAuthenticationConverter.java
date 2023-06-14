package com.winfred.mall.oauth2.base;

import com.winfred.mall.oauth2.utils.OAuth2EndpointUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义 认证转换器
 * <p>
 * request -> authentication
 *
 * @author winfred958
 */
public abstract class AbstractAuth2BaseAuthenticationConverter<T extends AbstractAuth2BaseAuthenticationToken>
    implements AuthenticationConverter, AuthenticationConverterExt {


  /**
   * 构造自定义 token
   *
   * @param authentication
   * @param scopes
   * @param additionalAttrs
   * @return
   */
  public abstract T buildToken(Authentication authentication, Set<String> scopes, Map<String, Object> additionalAttrs);

  @Override
  public Authentication convert(HttpServletRequest request) {

    // 判断认证类型, 如果不支持, 则返回null?
    if (!support(request)) {
      return null;
    }
    // 获取当前已经认证的客户端信息
    Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
    if (null == clientPrincipal) {
      throw new BadCredentialsException("未检测到认证信息.");
    }

    MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);
    // scope (OPTIONAL)
    String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
    if (StringUtils.hasText(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
      OAuth2EndpointUtils.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.SCOPE,
          OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
    }
    Set<String> requestedScopes = null;
    if (StringUtils.hasText(scope)) {
      requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
    }

    // 扩展信息
    Map<String, Object> additionalParameters = parameters.entrySet()
        .stream()
        .filter(e -> !e.getKey().equals(OAuth2ParameterNames.GRANT_TYPE)
            && !e.getKey().equals(OAuth2ParameterNames.SCOPE))
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));

    return buildToken(clientPrincipal, requestedScopes, additionalParameters);
  }

}
