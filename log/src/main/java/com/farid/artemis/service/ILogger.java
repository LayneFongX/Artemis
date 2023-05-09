package com.farid.artemis.service;


import com.farid.artemis.enums.log.LoggerTypeEnum;
import com.farid.artemis.domain.biz.log.LogLevelUpdateVO;

import java.util.List;


public interface ILogger {

    LoggerTypeEnum getLogType();

    /**
     * 初始化的时候获取当前已经配置的logger，方便后面重制配置
     */
    void init();

    /**
     * 修改日志级别
     */
    void setLevel(List<LogLevelUpdateVO> logLevelUpdateVOS);

    /**
     * 回复日志启动时的日志级别
     */
    void reset();
}
