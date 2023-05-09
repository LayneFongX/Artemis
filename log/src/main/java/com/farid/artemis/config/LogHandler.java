package com.farid.artemis.config;

import com.alibaba.fastjson.JSON;
import com.farid.artemis.domain.biz.log.UpdateLevel;
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
    public static final String CLASSES_LOG_LEVEL = "[{\"logName\":\"com.tuya.europa.service.kafkamq.base.AbstractBizConsumer\",\"level\":\"OFF\"}]";

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

        List<UpdateLevel> updateLevels = JSON.parseArray(CLASSES_LOG_LEVEL, UpdateLevel.class);
        if (CollectionUtils.isEmpty(updateLevels)) {
            logger.reset();
            return;
        }

        logger.setLevel(updateLevels);
    }
}
