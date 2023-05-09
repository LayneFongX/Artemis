package com.farid.artemis.service.impl;

import com.farid.artemis.domain.biz.log.LoggerType;
import com.farid.artemis.domain.biz.log.UpdateLevel;
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
    public LoggerType getLogType() {
        return LoggerType.LOG4J;
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
    public void setLevel(List<UpdateLevel> updateLevels) {
        for (UpdateLevel updateLevel : updateLevels) {
            Logger targetLogger = LogManager.getLogger(updateLevel.getLogName());
            if (Objects.nonNull(targetLogger)) {
                Level targetLevel = Level.toLevel(updateLevel.getLevel());
                targetLogger.setLevel(targetLevel);
            }
        }
    }
}
