package com.example.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengsong
 * @date 2020/4/16 2:58 下午
 */
public class ThreadPoolUtils {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void receiveTasks() throws InterruptedException {
        //executorService.invokeAll(tasks);
    }

}
