package com.farid.artemis.proxy;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@SuppressWarnings("all")
public class CacheProxy {

    @Resource
    private RedisTemplate redisTemplate;

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void sadd(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set<String> smget(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void hset(byte[] key, byte[] field, byte[] value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        return redisTemplate.opsForHash().multiGet(key, Arrays.asList(fields));
    }

    public Set<byte[]> smembers(byte[] key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Long sadd(byte[] key, byte[]... members) {
        return redisTemplate.opsForSet().add(key, members);
    }

    public Boolean exists(Object key) {
        return redisTemplate.hasKey(key);
    }

    public void watch(byte[]... keys) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.watch(keys);
            return null;
        });
    }

    public Object performTransaction(byte[] key, byte[] field, byte[] value) {
        return redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                operations.opsForHash().put((K) key, field, value);
                return operations.exec();
            }
        });
    }

    public Long time() {
        return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.time();
        });
    }

    public Long objectIdletime(byte[] key) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        byte[] rawKey = redisTemplate.getKeySerializer().serialize(Objects.requireNonNull(stringSerializer.deserialize(key)));

        return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            String command = "OBJECT";
            String subCommand = "IDLETIME";

            Object result = connection.execute(command, subCommand.getBytes(), rawKey);
            return (Long) result;
        });
    }

    public void deleteKey(String key){
        redisTemplate.delete(key);
    }

}