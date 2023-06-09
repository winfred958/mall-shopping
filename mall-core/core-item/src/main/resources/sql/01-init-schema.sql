CREATE DATABASE IF NOT EXISTS `im_item` CHARACTER SET 'utf8mb4';

CREATE TABLE IF NOT EXISTS `im_item`.`item_info`
(
    `id`                 BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `sku_id`             VARCHAR(24)  NOT NULL COMMENT 'stock keeping unit(库存量单位), SKU即库存进出计量的单位， 可以是以件、盒、托盘等为单位。',
    `spu_id`             VARCHAR(24)  NOT NULL COMMENT 'Standard Product Unit (标准化产品单元), SPU是商品信息聚合的最小单位，是一组可复用、易检索的标准化信息的集合，该集合描述了一个产品的特性。通俗点讲，属性值、特性相同的商品就可以称为一个SPU',
    `item_picture`       VARCHAR(200) COMMENT '商品主图',
    `sku_title`          VARCHAR(200) NOT NULL DEFAULT 1 COMMENT '卖点',
    `item_specification` VARCHAR(255)          DEFAULT NULL COMMENT '商品规格[size, color]',
    `update_user`        VARCHAR(64)           DEFAULT NULL COMMENT '编辑人',
    `create_timestamp`   BIGINT(13)   NOT NULL,
    `update_timestamp`   BIGINT(13)   NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uix_sku_id` (`sku_id`) USING BTREE,
    KEY `idx_spu_id` (`spu_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'sku表, 颜色, size等, (库存单元)';

CREATE TABLE IF NOT EXISTS `im_item`.`item_group`
(
    `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `spu_id`           VARCHAR(24)  NOT NULL COMMENT 'Standard Product Unit (标准化产品单元), SPU是商品信息聚合的最小单位，是一组可复用、易检索的标准化信息的集合，该集合描述了一个产品的特性。通俗点讲，属性值、特性相同的商品就可以称为一个SPU',
    `status`           INT(3)       NOT NULL DEFAULT 1 COMMENT '商品状态, 1: 上架, 2: 下架',
    `spu_title`        VARCHAR(200) NOT NULL DEFAULT 1 COMMENT '卖点',
    `category_id`      INT(11)      NOT NULL COMMENT '商品所属分类',
    `brand_id`         INT(11)      NOT NULL COMMENT '商品所属品牌',
    `agency_id`        INT(11)      NOT NULL COMMENT '经销商id (店铺)',
    `update_user`      VARCHAR(64)           DEFAULT NULL COMMENT '编辑人',
    `create_timestamp` BIGINT(13)   NOT NULL,
    `update_timestamp` BIGINT(13)   NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uix_spu_id` (`spu_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT 'spu 标准化产品单元, 厂商, 材质, 产地';

CREATE TABLE IF NOT EXISTS `im_item`.`item_description`
(
    `id`               BIGINT(20)  NOT NULL AUTO_INCREMENT,
    `spu_id`           VARCHAR(24) NOT NULL COMMENT '商品编号',
    `item_describe`    TEXT(64) COMMENT '商品详细描述',
    `update_user`      VARCHAR(64) DEFAULT NULL COMMENT '编辑人',
    `create_timestamp` BIGINT(13)  NOT NULL,
    `update_timestamp` BIGINT(13)  NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uix_spu_id` (`spu_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '商品描述';


CREATE TABLE IF NOT EXISTS `im_item`.`item_price`
(
    `id`               BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `sku_id`           VARCHAR(24)  NOT NULL COMMENT '商品编号',
    `cost_price`       VARCHAR(200) NOT NULL COMMENT '商品(初始/备案)进价',
    `selling_price`    VARCHAR(200) NOT NULL COMMENT '商品销售价',
    `batch_version`    VARCHAR(200) NOT NULL COMMENT '商品批次',
    `update_user`      VARCHAR(64) DEFAULT NULL COMMENT '编辑人',
    `create_timestamp` BIGINT(13)   NOT NULL,
    `update_timestamp` BIGINT(13)   NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uix_sku_id` (`sku_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '商品描述';

CREATE TABLE IF NOT EXISTS `im_item`.`item_category`
(
    `id`               INT(11)      NOT NULL AUTO_INCREMENT,
    `category_name`    VARCHAR(128) NOT NULL COMMENT '商品分类名称',
    `level`            INT(1)       NOT NULL COMMENT '几级分类1,2,3',
    `parent_id`        INT(11) DEFAULT NULL,
    `create_timestamp` BIGINT(13)   NOT NULL,
    `update_timestamp` BIGINT(13)   NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '商品分类';

CREATE TABLE IF NOT EXISTS `im_item`.`item_brand`
(
    `id`               INT(11)      NOT NULL AUTO_INCREMENT,
    `brand_name`       VARCHAR(200) NOT NULL COMMENT '商品品牌名称',
    `create_timestamp` BIGINT(13)   NOT NULL,
    `update_timestamp` BIGINT(13)   NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '商品品牌';

CREATE TABLE IF NOT EXISTS `im_item`.`shop_info`
(
    `id`               INT(11)      NOT NULL AUTO_INCREMENT,
    `shop_name`        VARCHAR(200) NOT NULL COMMENT '店铺名称',
    `create_timestamp` BIGINT(13)   NOT NULL,
    `update_timestamp` BIGINT(13)   NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT '商品品牌';