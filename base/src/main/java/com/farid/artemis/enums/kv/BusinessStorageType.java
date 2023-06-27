package com.farid.artemis.enums.kv;

public enum BusinessStorageType {

    INITIAL(0, "initial"),
    HOME(1, "home"),
    ROOM(2, "room"),
    DEVICE(3, "device"),
    DEVICE_GROUP(4, "deviceGroup"),
    USER(5, "user"),
    PRODUCT(6, "product"),
    APP(7, "app"),
    PROJECT(8, "project");


    private Integer code;

    private String msg;

    BusinessStorageType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BusinessStorageType getByCode(Integer code) {
        if (code != null) {
            for (BusinessStorageType unitEnum : BusinessStorageType.values()) {
                if (unitEnum.getCode().equals(code)) {
                    return unitEnum;
                }
            }
        }
        return null;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
