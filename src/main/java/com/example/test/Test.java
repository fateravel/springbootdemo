package com.example.test;

import com.example.config.TestConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        //int[] arr = {5, 10, 17, 36, 45, 50, 57, 64, 67, 73};
        int[] arr = createArr();
        //System.out.println(Arrays.toString(arr));
        //bubbleSort(arr);
        //qsort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    private static void qsort(int[] data, int start, int end) {
        int povit = data[start];//先给定分割数，默认取当前数组给定排序范围内的起始值
        int left = start;//给定从左边开始查找的起始索引
        int right = end;//给定从右边开始查找的起始索引
        while (left < right) {//本次遍历最终的目的是确定分割数的最终位置，确保左边的比它小右边的比他大
            while (left < right) {
                if (data[right] < povit) {//找到右边第一个比分割数小的数与左边的数进行交换来确保放到分割数的左边
                    int temp = data[right];
                    data[right] = data[left];
                    data[left] = temp;
                    break;
                }
                right--;
            }
            while (left < right) {
                if (data[left] > povit) {//找到左边第一个比中分割数大的数放到分割数的右边
                    int temp = data[right];
                    data[right] = data[left];
                    data[left] = temp;
                    break;
                }
                left++;
            }
        }
        System.out.println("---left:" + left + ",right:" + right + ",start:" + start + ",end:" + end);
        System.out.println(Arrays.toString(data));
        if (start < left - 1) {
            qsort(data, start, left - 1);
        }
        if (right + 1 < end) {
            qsort(data, right + 1, end);
        }
    }

    private static void bubbleSort(int[] arr) {
        //int[] arr = {5,4,6,2,3,1};
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    /*int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;*/
                    /*arr[j]  ^= arr[j+1];
                    arr[j+1] ^= arr[j] ;
                    arr[j] ^= arr[j+1];*/
                    arr[j] += arr[j+1];
                    arr[j+1] = arr[j] - arr[j+1];
                    arr[j] -= arr[j+1];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static int[] createArr() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

}
