package com.farid.artemis.service.impl;


import com.farid.artemis.enums.log.LoggerTypeEnum;
import com.farid.artemis.domain.biz.log.LogLevelUpdateVO;
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
    public LoggerTypeEnum getLogType() {
        return LoggerTypeEnum.LOG4J2;
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
    public void setLevel(List<LogLevelUpdateVO> logLevelUpdateVOS) {
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        for (LogLevelUpdateVO logLevelUpdateVO : logLevelUpdateVOS) {
            Logger logger = loggerContext.getLogger(logLevelUpdateVO.getLogName());
            if (Objects.nonNull(logger)) {
                Level targetLevel = Level.toLevel(logLevelUpdateVO.getLevel());
                logger.setLevel(targetLevel);
            }
        }
        loggerContext.updateLoggers();
    }
}
