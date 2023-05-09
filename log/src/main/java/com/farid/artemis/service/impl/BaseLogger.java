package com.farid.artemis.service.impl;

import com.farid.artemis.domain.biz.log.UpdateLevel;
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
        List<UpdateLevel> updateLevels = Lists.newArrayList();
        loggerMap.keySet().forEach(key -> {
            UpdateLevel updateLevel = new UpdateLevel();
            String level = loggerMap.get(key);
            updateLevel.setLogName(key);
            updateLevel.setLevel(level);
            updateLevels.add(updateLevel);
        });

        log.info("reset logger level {}", updateLevels);
        setLevel(updateLevels);
    }
}
