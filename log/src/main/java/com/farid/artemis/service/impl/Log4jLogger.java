package com.farid.artemis.service.impl;

import com.farid.artemis.enums.log.LoggerTypeEnum;
import com.farid.artemis.domain.biz.log.LogLevelUpdateVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class Log4jLogger extends BaseLogger {

    @Override
    public LoggerTypeEnum getLogType() {
        return LoggerTypeEnum.LOG4J;
    }

    @Override
    public void init() {
        Enumeration enumeration = LogManager.getCurrentLoggers();
        while (enumeration.hasMoreElements()) {
            Logger logger = (Logger) enumeration.nextElement();
            if (logger.getLevel() != null) {
                loggerMap.put(logger.getName(), logger.getLevel().toString());
            }
        }
        Logger rootLogger = LogManager.getRootLogger();
        loggerMap.put(rootLogger.getName(), rootLogger.getLevel().toString());
    }

    @Override
    public void setLevel(List<LogLevelUpdateVO> logLevelUpdateVOS) {
        for (LogLevelUpdateVO logLevelUpdateVO : logLevelUpdateVOS) {
            Logger targetLogger = LogManager.getLogger(logLevelUpdateVO.getLogName());
            if (Objects.nonNull(targetLogger)) {
                Level targetLevel = Level.toLevel(logLevelUpdateVO.getLevel());
                targetLogger.setLevel(targetLevel);
            }
        }
    }
}
