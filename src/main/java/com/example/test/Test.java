package com.example.test;

import com.example.util.ThreadLocalUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private static final Object wait = new Object();

    private static int count;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums2 = {-1,-2,-3,-4,-5};
        int i = maxSubArray(nums2);
        System.out.println(i);
    }

    public static int maxSubArray(int[] nums){
        int max = 0, sum = 0, oneMax = nums[0];
        for (int num : nums) {
            sum += num;
            if (sum > max) {
                max = sum;
            } else if (sum < 0) {
                sum = 0;
            }
            oneMax = num > oneMax ? num : oneMax;
        }
        max = max == 0 ? oneMax : max;
        return max;
    }

    private static void sort(int[] arr, int start) {
        if (start == arr.length - 1) {
            String result = changeArrToStr(arr);
            //条件校验判断
            if (!result.contains("35") && !result.contains("53") && result.charAt(2) != '4') {
                System.out.println(result);
                count++;
            }
        } else {
            List<Integer> check = new ArrayList<>();
            for (int i = start; i < arr.length; i++) {
                //去重判断，即当前两个不同位置的数要不同值才有交换的意义
                if (check.contains(arr[i])) {
                    continue;
                }
                check.add(arr[i]);
                swap(arr, start, i);
                sort(arr, start + 1);
                swap(arr, start, i);
            }
        }
    }

    private static void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    private static String changeArrToStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private void oom() throws InterruptedException {
        List<Byte[]> list = new ArrayList<>();
        while (true) {
            Thread.sleep(1000);
            Byte[] bytes = new Byte[1024 * 1024];
            list.add(bytes);
            System.out.println(list.size());
        }
    }

    public static void waitTest() throws InterruptedException {
        synchronized (wait) {
            System.out.println("当前线程停止了：" + Thread.currentThread().getName());
            wait.wait();
            System.out.println("当前线程恢复了:" + Thread.currentThread().getName());
        }
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

