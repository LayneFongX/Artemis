package com.farid.artemis.enums;

import org.apache.commons.lang3.StringUtils;

public enum EliqDateTypeEnum {
    // token授权类型
    YEAR(1, "年", "year"),
    MONTH(2, "月", "month"),
    DAY(3, "日", "day"),
    HOUR(4, "小时", "hour"),

    ;

    EliqDateTypeEnum(Integer code, String name, String groupBy) {
        this.code = code;
        this.name = name;
        this.groupBy = groupBy;
    }

    public static EliqDateTypeEnum getByCode(Integer code) {
        if (code != null) {
            for (EliqDateTypeEnum eliqDateTypeEnum : EliqDateTypeEnum.values()) {
                if (eliqDateTypeEnum.getCode().equals(code)) {
                    return eliqDateTypeEnum;
                }
            }
        }
        return null;
    }

    public static EliqDateTypeEnum getByGroupBy(String groupBy) {
        if (StringUtils.isNotBlank(groupBy)) {
            for (EliqDateTypeEnum eliqDateTypeEnum : EliqDateTypeEnum.values()) {
                if (eliqDateTypeEnum.getGroupBy().equals(groupBy)) {
                    return eliqDateTypeEnum;
                }
            }
        }
        return null;
    }

    private final Integer code;
    private final String name;
    private final String groupBy;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGroupBy() {
        return groupBy;
    }
}
