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
 * token信息 存储
 * </p>
 *
 * @author winfred
 * @since 2023-06-14T10:31:00
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("oauth2_authorization_server.oauth2_authorization")
public class Oauth2AuthorizationEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @TableField("registered_client_id")
  private String registeredClientId;

  @TableField("principal_name")
  private String principalName;

  @TableField("authorization_grant_type")
  private String authorizationGrantType;

  @TableField("attributes")
  private String attributes;

  @TableField("state")
  private String state;

  @TableField("authorization_code_value")
  private String authorizationCodeValue;

  @TableField("authorization_code_issued_at")
  private LocalDateTime authorizationCodeIssuedAt;

  @TableField("authorization_code_expires_at")
  private LocalDateTime authorizationCodeExpiresAt;

  @TableField("authorization_code_metadata")
  private String authorizationCodeMetadata;

  @TableField("access_token_value")
  private String accessTokenValue;

  @TableField("access_token_issued_at")
  private LocalDateTime accessTokenIssuedAt;

  @TableField("access_token_expires_at")
  private LocalDateTime accessTokenExpiresAt;

  @TableField("access_token_metadata")
  private String accessTokenMetadata;

  @TableField("access_token_type")
  private String accessTokenType;

  @TableField("access_token_scopes")
  private String accessTokenScopes;

  @TableField("oidc_id_token_value")
  private String oidcIdTokenValue;

  @TableField("oidc_id_token_issued_at")
  private LocalDateTime oidcIdTokenIssuedAt;

  @TableField("oidc_id_token_expires_at")
  private LocalDateTime oidcIdTokenExpiresAt;

  @TableField("oidc_id_token_metadata")
  private String oidcIdTokenMetadata;

  @TableField("refresh_token_value")
  private String refreshTokenValue;

  @TableField("refresh_token_issued_at")
  private LocalDateTime refreshTokenIssuedAt;

  @TableField("refresh_token_expires_at")
  private LocalDateTime refreshTokenExpiresAt;

  @TableField("refresh_token_metadata")
  private String refreshTokenMetadata;


  public static final String ID = "id";

  public static final String REGISTERED_CLIENT_ID = "registered_client_id";

  public static final String PRINCIPAL_NAME = "principal_name";

  public static final String AUTHORIZATION_GRANT_TYPE = "authorization_grant_type";

  public static final String ATTRIBUTES = "attributes";

  public static final String STATE = "state";

  public static final String AUTHORIZATION_CODE_VALUE = "authorization_code_value";

  public static final String AUTHORIZATION_CODE_ISSUED_AT = "authorization_code_issued_at";

  public static final String AUTHORIZATION_CODE_EXPIRES_AT = "authorization_code_expires_at";

  public static final String AUTHORIZATION_CODE_METADATA = "authorization_code_metadata";

  public static final String ACCESS_TOKEN_VALUE = "access_token_value";

  public static final String ACCESS_TOKEN_ISSUED_AT = "access_token_issued_at";

  public static final String ACCESS_TOKEN_EXPIRES_AT = "access_token_expires_at";

  public static final String ACCESS_TOKEN_METADATA = "access_token_metadata";

  public static final String ACCESS_TOKEN_TYPE = "access_token_type";

  public static final String ACCESS_TOKEN_SCOPES = "access_token_scopes";

  public static final String OIDC_ID_TOKEN_VALUE = "oidc_id_token_value";

  public static final String OIDC_ID_TOKEN_ISSUED_AT = "oidc_id_token_issued_at";

  public static final String OIDC_ID_TOKEN_EXPIRES_AT = "oidc_id_token_expires_at";

  public static final String OIDC_ID_TOKEN_METADATA = "oidc_id_token_metadata";

  public static final String REFRESH_TOKEN_VALUE = "refresh_token_value";

  public static final String REFRESH_TOKEN_ISSUED_AT = "refresh_token_issued_at";

  public static final String REFRESH_TOKEN_EXPIRES_AT = "refresh_token_expires_at";

  public static final String REFRESH_TOKEN_METADATA = "refresh_token_metadata";

}
