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
@TableName("mall_customer.role_permission")
public class RolePermissionEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 角色id
   */
  @TableField("role_id")
  private Integer roleId;

  /**
   * 权限id
   */
  @TableField("permission_id")
  private Integer permissionId;

  /**
   * 权限策略 0: ALLOW, 1: REJECT
   */
  @TableField("permission_strategy")
  private String permissionStrategy;

  @TableField("create_time")
  private LocalDateTime createTime;

  @TableField("update_time")
  private LocalDateTime updateTime;


  public static final String ID = "id";

  public static final String ROLE_ID = "role_id";

  public static final String PERMISSION_ID = "permission_id";

  public static final String PERMISSION_STRATEGY = "permission_strategy";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

}
