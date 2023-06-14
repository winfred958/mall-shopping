package com.winfred.mall.oauth2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.winfred.common.entity.BasePageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * client 注册信息, 用来校验client合法性
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oauth2_authorization_server.oauth2_registered_client")
public class Oauth2RegisteredClientEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @TableField("client_id")
  private String clientId;

  @TableField("client_id_issued_at")
  private LocalDateTime clientIdIssuedAt;

  @TableField("client_secret")
  private String clientSecret;

  @TableField("client_secret_expires_at")
  private LocalDateTime clientSecretExpiresAt;

  @TableField("client_name")
  private String clientName;

  @TableField("client_authentication_methods")
  private String clientAuthenticationMethods;

  @TableField("authorization_grant_types")
  private String authorizationGrantTypes;

  @TableField("redirect_uris")
  private String redirectUris;

  @TableField("scopes")
  private String scopes;

  @TableField("client_settings")
  private String clientSettings;

  @TableField("token_settings")
  private String tokenSettings;


  public static final String ID = "id";

  public static final String CLIENT_ID = "client_id";

  public static final String CLIENT_ID_ISSUED_AT = "client_id_issued_at";

  public static final String CLIENT_SECRET = "client_secret";

  public static final String CLIENT_SECRET_EXPIRES_AT = "client_secret_expires_at";

  public static final String CLIENT_NAME = "client_name";

  public static final String CLIENT_AUTHENTICATION_METHODS = "client_authentication_methods";

  public static final String AUTHORIZATION_GRANT_TYPES = "authorization_grant_types";

  public static final String REDIRECT_URIS = "redirect_uris";

  public static final String SCOPES = "scopes";

  public static final String CLIENT_SETTINGS = "client_settings";

  public static final String TOKEN_SETTINGS = "token_settings";

}
