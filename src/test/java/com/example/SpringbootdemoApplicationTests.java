package com.example;

import com.example.component.HelloSender;
import com.example.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    RedisDao redisDao;
    @Autowired
    private HelloSender helloSender;

    @Test
    public void testRedis() {
        redisDao.setKey("name_2", "ps-20190819");
        redisDao.setKey("age_2", "27");
        System.out.println("-----------" + redisDao.getValue("name"));
        System.out.println("-----------" + redisDao.getValue("age"));
    }

    @Test
    public void getValue() {
        System.out.println("-----------" + redisDao.getValue("name"));
        System.out.println("-----------" + redisDao.getValue("age"));
    }

    @Test
    public void hello() {
        helloSender.send();
    }

}
