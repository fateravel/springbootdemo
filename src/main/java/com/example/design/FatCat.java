package com.example.design;

/**
 * @author pengsong
 * @date 2020/4/7 1:27 下午
 */
public class FatCat implements ICat {

    @Override
    public void eat(String food) {
        System.out.println("肥猫吃东西啦：" + food);
    }
}
