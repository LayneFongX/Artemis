package com.farid.artemis.enums.log;

/**
 * @author falcon
 * @since 2021/11/5
 */
public enum LoggerTypeEnum {
    LOG4J("org.slf4j.impl.Log4jLoggerFactory"),
    LOG4J2("org.apache.logging.slf4j.Log4jLoggerFactory"),
    LOGBACK("ch.qos.logback.classic.util.ContextSelectorStaticBinder"),
    UNKNOWN("UNKNOWN"),
    ;

    private String logName;

    LoggerTypeEnum(String logName) {
        this.logName = logName;
    }

    public String getLogName() {
        return logName;
    }
}
