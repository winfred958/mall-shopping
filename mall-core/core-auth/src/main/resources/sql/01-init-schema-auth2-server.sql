DROP DATABASE IF EXISTS `oauth2_authorization_server`;
CREATE DATABASE IF NOT EXISTS `oauth2_authorization_server` CHARACTER SET 'utf8mb4';

USE `oauth2_authorization_server`;

CREATE TABLE IF NOT EXISTS oauth2_registered_client
(
    id                            INT(11)       NOT NULL AUTO_INCREMENT,
    client_id                     VARCHAR(100)  NOT NULL,
    client_id_issued_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '',
    client_secret                 VARCHAR(500),
    client_secret_expires_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_name                   VARCHAR(200)  NOT NULL,
    client_authentication_methods VARCHAR(1000) NOT NULL,
    authorization_grant_types     VARCHAR(1000) NOT NULL,
    redirect_uris                 TEXT(16),
    scopes                        VARCHAR(1000) NOT NULL,
    client_settings               VARCHAR(2000) NOT NULL,
    token_settings                VARCHAR(2000) NOT NULL,
    PRIMARY KEY (id),
    KEY `idx_client_id` (`client_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='client 注册信息, 用来校验client合法性';

CREATE TABLE IF NOT EXISTS oauth2_authorization
(
    id                            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    registered_client_id          VARCHAR(100) NOT NULL,
    principal_name                VARCHAR(200) NOT NULL,
    authorization_grant_type      VARCHAR(100) NOT NULL,
    attributes                    TEXT          DEFAULT NULL,
    state                         VARCHAR(500)  DEFAULT NULL,
    authorization_code_value      VARCHAR(200)  DEFAULT NULL,
    authorization_code_issued_at  TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    authorization_code_expires_at TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    authorization_code_metadata   VARCHAR(200)  DEFAULT NULL,
    access_token_value            VARCHAR(200)  DEFAULT NULL,
    access_token_issued_at        TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    access_token_expires_at       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    access_token_metadata         VARCHAR(200)  DEFAULT NULL,
    access_token_type             VARCHAR(100)  DEFAULT NULL,
    access_token_scopes           VARCHAR(1000) DEFAULT NULL,
    oidc_id_token_value           VARCHAR(200)  DEFAULT NULL,
    oidc_id_token_issued_at       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    oidc_id_token_expires_at      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    oidc_id_token_metadata        VARCHAR(200)  DEFAULT NULL,
    refresh_token_value           VARCHAR(200)  DEFAULT NULL,
    refresh_token_issued_at       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    refresh_token_expires_at      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    refresh_token_metadata        VARCHAR(200)  DEFAULT NULL,
    PRIMARY KEY (id),
    KEY `idx_token` (access_token_value) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='token信息 存储';


CREATE TABLE IF NOT EXISTS oauth2_authorization_consent
(
    registered_client_id VARCHAR(100) NOT NULL,
    principal_name       VARCHAR(200) NOT NULL,
    authorities          TEXT         NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='token信息 存储';