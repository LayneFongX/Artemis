package com.farid.artemis.service;


import com.farid.artemis.domain.biz.log.LoggerType;
import com.farid.artemis.domain.biz.log.UpdateLevel;

import java.util.List;


public interface ILogger {

    LoggerType getLogType();

    /**
     * 初始化的时候获取当前已经配置的logger，方便后面重制配置
     */
    void init();

    /**
     * 修改日志级别
     */
    void setLevel(List<UpdateLevel> updateLevels);

    /**
     * 回复日志启动时的日志级别
     */
    void reset();
}
