package com.example.test;

import com.example.config.BeanConfig;
import com.example.util.ThreadLocalUtils;
import io.swagger.models.auth.In;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.Signature;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        ThreadLocalUtils.setLocal("strrrrrrr");
        ThreadLocalUtils.setLocal2(123321);
        test.testStr();
        test.testInt();
        Arrays.asList(new int[]{1, 2, 3});
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getBean("1as");
    }

    public void testStr() {
        System.out.println("testStr");
        System.out.println(ThreadLocalUtils.getStr());
    }

    public void testInt() {
        System.out.println("testInt");
        System.out.println(ThreadLocalUtils.getInt());
    }

}

