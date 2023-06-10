CREATE TABLE IF NOT EXISTS `mall_customer`.`user_address`
(
    `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `user_id`          BIGINT(20)   NOT NULL COMMENT '用户id',
    `province`         VARCHAR(260) COMMENT '省',
    `city`             VARCHAR(260) NOT NULL COMMENT '市',
    `county`           VARCHAR(260) NOT NULL DEFAULT '' COMMENT '区/县',
    `address`          TEXT COMMENT '地址',
    `create_timestamp` BIGINT       NOT NULL COMMENT '写入(注册)时间',
    `active_timestamp` BIGINT                DEFAULT 0 COMMENT '激活时间',
    `update_timestamp` BIGINT                DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `uix_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户信息';