package com.winfred.common.constant;

import java.time.ZoneId;

/**
 * @author winfred958
 */
public interface MallConstant {

  String TOKEN_KEY = "M_TOKEN";

  String ROLE_ANONYMOUS = "ANONYMOUS";

  String CUSTOM_CONSENT_PAGE_URI_CONFIRM_ACCESS = "/token/confirm_access";

  ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
}
