package com.farid.artemis.service.impl;

import com.farid.artemis.domain.biz.log.LogLevelUpdateVO;
import com.farid.artemis.service.ILogger;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class BaseLogger implements ILogger {

    protected Map<String, String> loggerMap = new ConcurrentHashMap<>();

    @Override
    public void reset() {
        List<LogLevelUpdateVO> logLevelUpdateVOS = Lists.newArrayList();
        loggerMap.keySet().forEach(key -> {
            LogLevelUpdateVO logLevelUpdateVO = new LogLevelUpdateVO();
            String level = loggerMap.get(key);
            logLevelUpdateVO.setLogName(key);
            logLevelUpdateVO.setLevel(level);
            logLevelUpdateVOS.add(logLevelUpdateVO);
        });

        log.info("reset logger level {}", logLevelUpdateVOS);
        setLevel(logLevelUpdateVOS);
    }
}
