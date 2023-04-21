package com.farid.artemis.enums;

public enum CloudBusinessType {
    SCHNEIDER("schneider");
    private String type;

    public static String BIZ_TYPE = "business";

    CloudBusinessType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
