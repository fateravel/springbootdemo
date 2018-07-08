package com.example.demo.dao;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {

    private final StringRedisTemplate template;

    public StringRedisTemplate getTemplate() {
        return template;
    }

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, 1, TimeUnit.MINUTES);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }
}
