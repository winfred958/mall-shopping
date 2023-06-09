DROP DATABASE IF EXISTS `ec_customer`;
CREATE DATABASE IF NOT EXISTS `ec_customer` CHARACTER SET 'utf8mb4';

-- 注册用户
CREATE TABLE IF NOT EXISTS `ec_customer`.`user_info`
(
    `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '自增, 无意义',
    `user_display_id`  VARCHAR(128) COMMENT '用户id, hash()',
    `display_name`     VARCHAR(200) NOT NULL COMMENT '昵称',
    `user_name`        VARCHAR(200) COMMENT '用户名',
    `email`            VARCHAR(200) COMMENT 'email',
    `email_status`     VARCHAR(10) COMMENT 'email 状态',
    `phone_num`        VARCHAR(200) COMMENT '电话号码',
    `phone_status`     VARCHAR(10) COMMENT 'phone 状态',
    `password`         VARCHAR(128) COMMENT '密码',
    `active_status`    INT(1)       NOT NULL DEFAULT 0 COMMENT '是否激活, 0:未激活, 1:激活',
    `register_time`    DATETIME     NOT NULL COMMENT '写入(注册)时间',
    `active_timestamp` DATETIME COMMENT '激活时间',
    `create_time`      DATETIME              DEFAULT CURRENT_TIMESTAMP,
    `update_time`      DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uix_user_name` (`user_name`) USING BTREE,
    UNIQUE KEY `uix_email` (`email`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户信息';


-- 第三方登录
CREATE TABLE IF NOT EXISTS `ec_customer`.`user_third_party`
(
    `open_id`          VARCHAR(200) NOT NULL COMMENT 'open_id',
    `platform`         VARCHAR(200) NOT NULL COMMENT '平台, WX, ZFB',
    `user_id`          BIGINT(20)   NOT NULL COMMENT '关联用户 id',
    `active_status`    INT(1)       NOT NULL DEFAULT 0 COMMENT '是否激活, 0:未激活, 1:激活',
    `register_time`    DATETIME     NOT NULL COMMENT '写入(注册)时间',
    `active_timestamp` DATETIME COMMENT '激活时间',
    `create_time`      DATETIME              DEFAULT CURRENT_TIMESTAMP,
    `update_time`      DATETIME ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`open_id`),
    UNIQUE KEY `uix_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户信息';


CREATE TABLE IF NOT EXISTS `ec_customer`.`group_info`
(
    `id`          INT(11)      NOT NULL AUTO_INCREMENT,
    `group_name`  VARCHAR(200) NOT NULL COMMENT '组名',
    `parent_id`   INT,
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_group_name` (group_name) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户组信息';


CREATE TABLE IF NOT EXISTS `ec_customer`.`user_group`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(20) NOT NULL COMMENT '用户id',
    `group_id`    INT(11)    NOT NULL COMMENT '',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_group_id` (`group_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户所属组关系';


CREATE TABLE IF NOT EXISTS `ec_customer`.`role_info`
(
    `id`            INT(11)      NOT NULL AUTO_INCREMENT,
    `role_name`     VARCHAR(200) NOT NULL COMMENT '角色名',
    `role_describe` TEXT COMMENT '描述',
    `create_time`   DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time`   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_name` (role_name) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';


CREATE TABLE IF NOT EXISTS `ec_customer`.`group_role`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `group_id`    INT(11)    NOT NULL COMMENT '用户id',
    `role_id`     INT(11)    NOT NULL COMMENT '角色id',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_group_id` (`group_id`) USING BTREE,
    KEY `idx_role_id` (`role_id`) USING BTREE,
    UNIQUE KEY `uk_group_role` (`group_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户组与角色关系(group通用role)';


CREATE TABLE IF NOT EXISTS `ec_customer`.`user_role`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT(20) NOT NULL COMMENT '用户id',
    `role_id`     INT        NOT NULL COMMENT '',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_group_id` (`user_id`) USING BTREE,
    KEY `idx_role_id` (`role_id`) USING BTREE,
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户与角色关系(user独立分配的role)';


CREATE TABLE IF NOT EXISTS `ec_customer`.`permission_info`
(
    `id`             INT(11)      NOT NULL AUTO_INCREMENT,
    `permission_key` VARCHAR(128) NOT NULL COMMENT '权限key',
    `describe`       TEXT COMMENT '描述',
    `create_time`    DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_permission_key` (`permission_key`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限信息';


CREATE TABLE IF NOT EXISTS `ec_customer`.`role_permission`
(
    `id`                  BIGINT(20)  NOT NULL AUTO_INCREMENT,
    `role_id`             INT(11)     NOT NULL COMMENT '角色id',
    `permission_id`       INT(11)     NOT NULL COMMENT '权限id',
    `permission_strategy` VARCHAR(60) NOT NULL DEFAULT 1 COMMENT '权限策略 0: ALLOW, 1: REJECT',
    `create_time`         DATETIME             DEFAULT CURRENT_TIMESTAMP,
    `update_time`         DATETIME             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`) USING BTREE,
    KEY `idx_permission_id` (`permission_id`) USING BTREE,
    UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色&权限关系';


CREATE TABLE IF NOT EXISTS `ec_customer`.`menu`
(
    `id`          INT(11)      NOT NULL AUTO_INCREMENT,
    `router_link` VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'router-link.to 的值, 跟路由为 /, 这里配置全路径',
    `name`        VARCHAR(200) NOT NULL DEFAULT '' COMMENT '显示名称',
    `sort_order`  INT(11)      NOT NULL DEFAULT 0 COMMENT '排序字段',
    `parent_id`   BIGINT(20),
    `create_time` DATETIME              DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色&权限关系';


CREATE TABLE IF NOT EXISTS `ec_customer`.`pre_menu`
(
    `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `permission_id` INT(11)    NOT NULL COMMENT '权限id',
    `menu_id`       INT(11)    NOT NULL DEFAULT 1 COMMENT '路由 path',
    `create_time`   DATETIME            DEFAULT CURRENT_TIMESTAMP,
    `update_time`   DATETIME            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `idx_permission_id` (`permission_id`) USING BTREE,
    KEY `idx_menu_id` (`menu_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色&权限关系';


INSERT INTO `ec_customer`.`role_info` (role_name, role_describe)
VALUES ('SYS_ADMIN', '系统管理'),
       ('SYS_ADMIN_READ_ONLY', '系统管理'),
       ('CUSTOMER', '客户'),
       ('CUSTOMER_ADMIN', '客户管理'),
       ('CUSTOMER_READ_ONLY', '客户只读'),
       ('ITEM_ADMIN', '商品管理'),
       ('ITEM_READ_ONLY', '商品只读'),
       ('ORDER_ADMIN', '客户管理'),
       ('ORDER_READ_ONLY', '客户只读'),
       ('MARKETING_ADMIN', '客户管理'),
       ('MARKETING_READ_ONLY', '客户只读'),
       ('ANONYMOUS', '访客')
;

INSERT INTO `ec_customer`.`permission_info` (permission_key, `describe`)
VALUES ('SYS_ADMIN', '系统管理'),
       ('SYS_ADMIN_READ_ONLY', '系统管理'),
       ('CUSTOMER', '客户'),
       ('CUSTOMER_ADMIN', '客户管理'),
       ('CUSTOMER_READ_ONLY', '客户只读'),
       ('ITEM_ADMIN', '商品管理'),
       ('ITEM_READ_ONLY', '商品只读'),
       ('ORDER_ADMIN', '客户管理'),
       ('ORDER_READ_ONLY', '客户只读'),
       ('MARKETING_ADMIN', '客户管理'),
       ('MARKETING_READ_ONLY', '客户只读')
;