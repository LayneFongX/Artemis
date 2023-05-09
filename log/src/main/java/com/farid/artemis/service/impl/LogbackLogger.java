package com.farid.artemis.service.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.farid.artemis.enums.log.LoggerTypeEnum;
import com.farid.artemis.domain.biz.log.LogLevelUpdateVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Slf4j
@Component
public class LogbackLogger extends BaseLogger {

    private final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

    @Override
    public LoggerTypeEnum getLogType() {
        return LoggerTypeEnum.LOGBACK;
    }

    public void init() {
    }

    @Override
    public void setLevel(List<LogLevelUpdateVO> logLevelUpdateVOS) {
        for (LogLevelUpdateVO logLevelUpdateVO : logLevelUpdateVOS) {
            Logger targetLogger = loggerContext.getLogger(logLevelUpdateVO.getLogName());

            storeLoggerHistoryLevel(logLevelUpdateVO, targetLogger);

            Level targetLevel = Level.toLevel(logLevelUpdateVO.getLevel());
            targetLogger.setLevel(targetLevel);
        }
    }

    @Override
    public void reset() {
        super.reset();
        loggerMap.clear();
    }

    /**
     * 记录每次修改的logger和日志级别，清除的时候使用
     */
    private void storeLoggerHistoryLevel(LogLevelUpdateVO logLevelUpdateVO, Logger targetLogger) {
        Level level = targetLogger.getEffectiveLevel();
        if (Objects.nonNull(level)) {
            loggerMap.put(logLevelUpdateVO.getLogName(), level.toString());
        }
    }
}
