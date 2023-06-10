package com.winfred.mall.customer.entity;

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
 * 角色&权限关系
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("mall_customer.menu")
public class MenuEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * router-link.to 的值, 跟路由为 /, 这里配置全路径
   */
  @TableField("router_link")
  private String routerLink;

  /**
   * 显示名称
   */
  @TableField("name")
  private String name;

  /**
   * 排序字段
   */
  @TableField("sort_order")
  private Integer sortOrder;

  @TableField("parent_id")
  private Long parentId;

  @TableField("create_time")
  private LocalDateTime createTime;

  @TableField("update_time")
  private LocalDateTime updateTime;


  public static final String ID = "id";

  public static final String ROUTER_LINK = "router_link";

  public static final String NAME = "name";

  public static final String SORT_ORDER = "sort_order";

  public static final String PARENT_ID = "parent_id";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

}
