package com.example.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author pengsong
 * @date 2020/4/16 2:58 下午
 */
public class ThreadPoolUtils {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void receiveTasks() throws InterruptedException {
        //executorService.invokeAll(tasks);
    }

    public static void main(String[] args) {

    }

}
