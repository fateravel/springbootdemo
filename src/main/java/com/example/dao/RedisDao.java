package com.example.dao;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {

    private final StringRedisTemplate strTemplate;

    public RedisDao(StringRedisTemplate strTemplate) {
        this.strTemplate = strTemplate;
    }

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = strTemplate.opsForValue();
        ops.set(key, value, 1, TimeUnit.HOURS);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.strTemplate.opsForValue();
        return ops.get(key);
    }
}
