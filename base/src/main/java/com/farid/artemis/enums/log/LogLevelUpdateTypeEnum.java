package com.farid.artemis.enums.log;

public enum LogLevelUpdateTypeEnum {
    ALL(1),
    PACKAGE(2),
    CLASS(3);

    LogLevelUpdateTypeEnum(int type) {
        this.type = type;
    }

    public int type;

    public int getType() {
        return type;
    }
}
