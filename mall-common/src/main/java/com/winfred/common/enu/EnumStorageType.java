package com.winfred.common.enu;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum EnumStorageType {
  MySQL("MySQL");

  private String typeName;

  EnumStorageType(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  private static final Map<String, EnumStorageType> MAP = new HashMap<>();

  static {
    Arrays.stream(EnumStorageType
            .values())
        .forEach(type -> {
          MAP.put(type.getTypeName(), type);
          MAP.put(StringUtils.lowerCase(type.getTypeName()), type);
          MAP.put(StringUtils.upperCase(type.getTypeName()), type);
        });
  }

  public static EnumStorageType getByType(String typeName) {
    return MAP.get(typeName) == null ? EnumStorageType.MySQL : MAP.get(typeName);
  }
}
