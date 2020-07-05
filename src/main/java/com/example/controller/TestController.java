package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Authorize
@Slf4j
@Api("测试类")
@RequestMapping("api/test")
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Map<String, RLock> lockMap = new HashMap<>();

    @GetMapping("/starter")
    public Object starter(@RequestParam("id") String id) {
        User user = userMapper.findById(Long.parseLong(id));
        return user;
    }

    @GetMapping("/redis/lock")
    public Object lock(String key, String value) {
        /*RLock lock = redisson.getLock(key);
        lockMap.put(key, lock);
        lock.lock(30, TimeUnit.SECONDS);*/
        stringRedisTemplate.opsForValue().setIfAbsent(key,value);
        stringRedisTemplate.expire(key, 30, TimeUnit.SECONDS);
        stringRedisTemplate.exec();
        return key + "已上锁";
    }

    @GetMapping("/redis/unlock")
    public Object unlock(String key) {
        /*RLock lock = lockMap.get(key);
        lock.unlock();*/
        stringRedisTemplate.delete(key);
        return key + "已释放锁";
    }

    @GetMapping("/oom")
    public Object oom() {
        List<UserData> users = new ArrayList<>(Short.MAX_VALUE);
        /*new Thread(() -> {
            for (int i = 0; i < Short.MAX_VALUE; i++) {
                users.add(new UserData(new byte[1024 * 1024]));
                System.out.println("list size = " + users.size());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            users.add(new UserData(new byte[1024 * 1024]));
            System.out.println("list size = " + users.size());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    private class UserData {

        private byte[] data;

        private UserData(byte[] data) {
            this.data = data;
        }
    }
}
