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
 * 用户信息
 * </p>
 *
 * @author winfred
 * @since 2023-05-30T16:51:25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("mall_customer.user_info")
public class UserInfoEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  /**
   * 自增, 无意义
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 昵称
   */
  @TableField("display_name")
  private String displayName;

  /**
   * 用户名
   */
  @TableField("user_name")
  private String userName;

  /**
   * email
   */
  @TableField("email")
  private String email;

  /**
   * 电话号码
   */
  @TableField("phone_num")
  private String phoneNum;

  /**
   * 密码
   */
  @TableField("password")
  private String password;

  /**
   * 是否激活, 0:未激活, 1:激活
   */
  @TableField("active_status")
  private Integer activeStatus;

  /**
   * 写入(注册)时间
   */
  @TableField("register_time")
  private LocalDateTime registerTime;

  /**
   * 激活时间
   */
  @TableField("active_timestamp")
  private LocalDateTime activeTimestamp;

  @TableField("create_time")
  private LocalDateTime createTime;

  @TableField("update_time")
  private LocalDateTime updateTime;


  public static final String ID = "id";

  public static final String USER_DISPLAY_ID = "user_display_id";

  public static final String DISPLAY_NAME = "display_name";

  public static final String USER_NAME = "user_name";

  public static final String EMAIL = "email";

  public static final String PHONE_NUM = "phone_num";

  public static final String PASSWORD = "password";

  public static final String ACTIVE_STATUS = "active_status";

  public static final String REGISTER_TIME = "register_time";

  public static final String ACTIVE_TIMESTAMP = "active_timestamp";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

}
