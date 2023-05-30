package com.farid.artemis.service.impl;

import com.farid.artemis.service.IRedisService;
import com.farid.artemis.proxy.CacheProxy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Banchao
 * @Date 2023/5/5 18:05
 */

@Service
public class RedisService implements IRedisService {

    @Resource
    private CacheProxy cacheProxy;

    @Override
    public void add(String key, String value) {
        // cacheProxy.set(key, value);
    }
}
