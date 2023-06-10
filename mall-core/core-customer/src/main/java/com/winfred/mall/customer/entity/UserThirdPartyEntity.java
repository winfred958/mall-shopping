package com.winfred.mall.customer.entity;

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
@TableName("mall_customer.user_third_party")
public class UserThirdPartyEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  /**
   * open_id
   */
  @TableId("open_id")
  private String openId;

  /**
   * 平台, WX, ZFB
   */
  @TableField("platform")
  private String platform;

  /**
   * 关联用户 id
   */
  @TableField("user_id")
  private Long userId;

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


  public static final String OPEN_ID = "open_id";

  public static final String PLATFORM = "platform";

  public static final String USER_ID = "user_id";

  public static final String ACTIVE_STATUS = "active_status";

  public static final String REGISTER_TIME = "register_time";

  public static final String ACTIVE_TIMESTAMP = "active_timestamp";

  public static final String CREATE_TIME = "create_time";

  public static final String UPDATE_TIME = "update_time";

}
