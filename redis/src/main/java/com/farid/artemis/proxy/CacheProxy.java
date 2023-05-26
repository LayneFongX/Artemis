package com.farid.artemis.proxy;

import org.checkerframework.checker.units.qual.K;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings("all")
public class CacheProxy {

    @Resource
    private RedisTemplate redisTemplate;

    public <K, V> void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    public <K, V> void set(K key, V value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }


    public void batchSet(Map keyValueMap) {
        redisTemplate.opsForValue().multiSet(keyValueMap);
    }


    public <K, V> Boolean setIfAbsent(K key, V value, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
    }

    public <V, K> V get(K key) {
        return (V) redisTemplate.opsForValue().get(key);
    }
    
    public <K> Long getLong(K key) {
        Number value = this.get(key);
        if (value == null) {
            return null;
        }
        return value.longValue();
    }
    
    public <K> Integer getInteger(K key) {
        Number value = this.get(key);
        if (value == null) {
            return null;
        }
        return value.intValue();
    }

    public void increment(String key, long delta) {
        redisTemplate.opsForValue().increment(key, delta);
    }


    public void delete(String key) {
        redisTemplate.delete(key);
    }


    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public void deleteBatchSamePrefix(Set<String> cacheKeys) {
        redisTemplate.delete(cacheKeys);
    }

    public <K> Boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }


    public <K> boolean tryLock(K key, long waitTime, long leaseTime) {
        // return redisTemplate.opsForValue().tryLock(key, waitTime, leaseTime);
        return false;
    }


    public <K> void unlock(K key) {
        // redisTemplate.opsForValue().unlock(key);
    }

    public <K, HK, V> void putHash(K key, HK hashKey, V value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }


    public <K, HK> HK getHash(K key, Object hashKey) {
        return (HK) redisTemplate.opsForHash().get(key, hashKey);
    }


    public void deleteHash(K key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }


    public <K> Boolean expire(K key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }


    public <K, V> List<V> opsListRange(K key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    public <K> Long opsListSize(K key) {
        return redisTemplate.opsForList().size(key);
    }
}