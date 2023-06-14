package com.winfred.mall.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.winfred.common.entity.BasePageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * token信息 存储
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oauth2_authorization_server.oauth2_authorization_consent")
public class Oauth2AuthorizationConsentEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableField("registered_client_id")
  private String registeredClientId;

  @TableField("principal_name")
  private String principalName;

  @TableField("authorities")
  private String authorities;


  public static final String REGISTERED_CLIENT_ID = "registered_client_id";

  public static final String PRINCIPAL_NAME = "principal_name";

  public static final String AUTHORITIES = "authorities";

}
