CREATE
    DATABASE IF NOT EXISTS `mall_order` CHARACTER SET 'utf8mb4';


CREATE TABLE IF NOT EXISTS `mall_order`.`order_info`
(
    `id`
                       BIGINT
                                                                                    NOT
                                                                                        NULL
        AUTO_INCREMENT,
    `order_sn`
                       VARCHAR(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
    `user_id`          INT                                                          NOT NULL COMMENT '用户id',
    `order_status`     INT                                                          NOT NULL DEFAULT '1' COMMENT '订单状态',
    `order_amount`     DOUBLE(18,
                           2)                                                       NOT NULL COMMENT '订单金额',
    `create_timestamp` BIGINT                                                       NOT NULL,
    `crate_datetime`   DATETIME                                                     NOT NULL COMMENT '订单创建时间',
    `update_timestamp` BIGINT                                                       NOT NULL,
    `update_datetime`  DATETIME                                                     NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单更新时间',
    PRIMARY KEY
        (
         `id`
            ),
    UNIQUE KEY `uix_order_sn`
        (
         `order_sn`
            )
        USING BTREE,
    KEY `idx_order_status`
        (
         `order_status`
            )
        USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='订单表';


#
CREATE TABLE IF NOT EXISTS `mall_order`.`order_item`
    #
(
    # id int
    #
)
#;
#
#
CREATE TABLE IF NOT EXISTS `mall_order`.`order_shipping`
    #
(
    # id int
    #
)
#;
