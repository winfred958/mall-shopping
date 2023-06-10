package com.winfred.mall.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.winfred.common.entity.BasePageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@TableName("mall_customer.user_address")
public class UserAddressEntity extends BasePageRequest {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 用户id
   */
  @TableField("user_id")
  private Long userId;

  /**
   * 省
   */
  @TableField("province")
  private String province;

  /**
   * 市
   */
  @TableField("city")
  private String city;

  /**
   * 区/县
   */
  @TableField("county")
  private String county;

  /**
   * 地址
   */
  @TableField("address")
  private String address;

  /**
   * 写入(注册)时间
   */
  @TableField("create_timestamp")
  private Long createTimestamp;

  /**
   * 激活时间
   */
  @TableField("active_timestamp")
  private Long activeTimestamp;

  /**
   * 更新时间
   */
  @TableField("update_timestamp")
  private Long updateTimestamp;


  public static final String ID = "id";

  public static final String USER_ID = "user_id";

  public static final String PROVINCE = "province";

  public static final String CITY = "city";

  public static final String COUNTY = "county";

  public static final String ADDRESS = "address";

  public static final String CREATE_TIMESTAMP = "create_timestamp";

  public static final String ACTIVE_TIMESTAMP = "active_timestamp";

  public static final String UPDATE_TIMESTAMP = "update_timestamp";

}
