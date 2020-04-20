package com.example.test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author pengsong
 * @date 2020/4/15 3:49 下午
 */
public class TaskQueue {

    Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue = new TaskQueue();
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始添加任务...");
            taskQueue.addTask("tttttt");
        });
        thread.start();
        String task = taskQueue.getTask();
        System.out.println(task);
    }

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notify();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}
