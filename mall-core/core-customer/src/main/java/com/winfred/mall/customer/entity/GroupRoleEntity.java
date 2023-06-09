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
 * 用户组与角色关系(group通用role)
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ec_customer.group_role")
public class GroupRoleEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 用户id
   */
  @TableField("group_id")
  private Integer groupId;

  /**
   * 角色id
   */
  @TableField("role_id")
  private Integer roleId;

  @TableField("create_time")
  private LocalDateTime createTime;

  @TableField("update_time")
  private LocalDateTime updateTime;


  public static final String ID = "id";

  public static final String GROUP_ID = "group_id";

  public static final String ROLE_ID = "role_id";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

}
