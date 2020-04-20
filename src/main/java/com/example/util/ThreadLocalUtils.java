package com.example.util;

/**
 * @author pengsong
 * @date 2020/4/16 1:24 下午
 */
public class ThreadLocalUtils {

    static ThreadLocal<String> local = new ThreadLocal<>();
    static ThreadLocal<Integer> local2 = new ThreadLocal<>();

    public static void setLocal(String string) {
        local.set(string);
    }

    public static void setLocal2(Integer integer) {
        local2.set(integer);
    }

    public static String getStr() {
        return local.get();
    }

    public static Integer getInt() {
        return local2.get();
    }
}
