package com.winfred.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author winfred
 */
public class BasePageRequest extends PageModel implements Serializable {

  private static final long serialVersionUID = -5700842088869071012L;

  @Getter
  @Setter
  @TableField(exist = false)
  private List<Order> orders;

  @Getter
  @Setter
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(exist = false)
  private String startDateStr;

  @Getter
  @Setter
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @TableField(exist = false)
  private String endDateStr;
}
