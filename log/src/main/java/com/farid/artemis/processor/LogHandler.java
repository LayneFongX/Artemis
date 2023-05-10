package com.farid.artemis.processor;

import com.alibaba.fastjson.JSON;
import com.farid.artemis.domain.biz.log.LogLevelUpdateVO;
import com.farid.artemis.service.ILogger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LogHandler {
    public static final String CLASSES_LOG_LEVEL = "[{\"logName\":\"org.mongodb.driver\",\"level\":\"OFF\"}]";

    private final Map<String, ILogger> loggerMap;

    private ILogger logger;

    public LogHandler(List<ILogger> loggers) {
        this.loggerMap = loggers.stream().collect(
                Collectors.toMap(iLogger -> iLogger.getLogType().getLogName(), iLogger -> iLogger));
    }

    @PostConstruct
    public void init() {
        String type = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
        logger = loggerMap.get(type);
        if (Objects.isNull(logger)) {
            return;
        }
        logger.init();

        List<LogLevelUpdateVO> logLevelUpdateVOS = JSON.parseArray(CLASSES_LOG_LEVEL, LogLevelUpdateVO.class);
        if (CollectionUtils.isEmpty(logLevelUpdateVOS)) {
            logger.reset();
            return;
        }

        logger.setLevel(logLevelUpdateVOS);
    }
}
