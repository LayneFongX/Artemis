package com.farid.artemis.service.impl;


import com.farid.artemis.domain.biz.log.LoggerType;
import com.farid.artemis.domain.biz.log.UpdateLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class Log4j2Logger extends BaseLogger {

    @Override
    public LoggerType getLogType() {
        return LoggerType.LOG4J2;
    }

    public void init() {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Map<String, LoggerConfig> map = loggerContext.getConfiguration().getLoggers();
        for (LoggerConfig loggerConfig : map.values()) {
            String key = loggerConfig.getName();
            if (StringUtils.isBlank(key)) {
                key = "root";
            }
            loggerMap.put(key, loggerConfig.getLevel().toString());
        }
    }

    @Override
    public void setLevel(List<UpdateLevel> updateLevels) {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        for (UpdateLevel updateLevel : updateLevels) {
            Logger logger = loggerContext.getLogger(updateLevel.getLogName());
            if (Objects.nonNull(logger)) {
                Level targetLevel = Level.toLevel(updateLevel.getLevel());
                logger.setLevel(targetLevel);
            }
        }
        loggerContext.updateLoggers();
    }
}
