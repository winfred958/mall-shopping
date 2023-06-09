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
 * 权限信息
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ec_customer.permission_info")
public class PermissionInfoEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  /**
   * 权限key
   */
  @TableField("permission_key")
  private String permissionKey;

  /**
   * 描述
   */
  @TableField("describe")
  private String describe;

  @TableField("create_time")
  private LocalDateTime createTime;

  @TableField("update_time")
  private LocalDateTime updateTime;


  public static final String ID = "id";

  public static final String PERMISSION_KEY = "permission_key";

  public static final String DESCRIBE = "describe";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

}
