package com.example.design;

/**
 * @author pengsong
 * @date 2020/4/7 12:17 下午
 */
public class GunDog implements IDog {

    @Override
    public void run(int speed) {
        System.out.println("猎狗在奔跑！！！！！speed:" + speed);
    }
}
