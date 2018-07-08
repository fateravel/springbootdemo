package com.example.demo;

import com.example.demo.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void contextLoads() {
    }

    private final RedisDao redisDao;

    public SpringbootdemoApplicationTests(RedisDao redisDao) {
        this.redisDao = redisDao;
    }

    @Test
    public void testRedis() {
        redisDao.setKey("name", "pengsong");
        redisDao.setKey("age", "26");
        System.out.println("-----------" + redisDao.getValue("name"));
        System.out.println("-----------" + redisDao.getValue("age"));
    }

    @Test
    public void getValue() {
        System.out.println("-----------" + redisDao.getValue("name"));
        System.out.println("-----------" + redisDao.getValue("age"));
    }

}
