package com.example.design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author pengsong
 * @date 2020/4/7 12:49 下午
 */
public class JdkProxy implements InvocationHandler{

    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置处理：method:" + method);
        for (Object object : args) {
            System.out.println(object.getClass() + " ----> value:" + object);
        }
        System.out.println("---------------------------");
        method.invoke(target, args);
        System.out.println("---------------------------");
        System.out.println("后置处理");
        return null;
    }

    public Object getProxy() {
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
        return proxyInstance;
    }

    public static void main(String[] args) {
        //IDog dog = new GunDog();
        JdkProxy jdkProxy = new JdkProxy(new GunDog());
        IDog proxy = (IDog) jdkProxy.getProxy();
        proxy.run(18);
        /*System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ICat cat = new FatCat();
        ICat proxy1 = getProxy(cat);
        proxy1.eat("fish!!!!!!!!!!!!!");*/
    }
}

interface ICat {
    void eat(String food);
}

interface IDog {

    void run(int speed);
}

class FatCat implements ICat {

    @Override
    public void eat(String food) {
        System.out.println("肥猫吃东西啦：" + food);
    }
}

class GunDog implements IDog {

    @Override
    public void run(int speed) {
        System.out.println("猎狗在奔跑！！！！！speed:" + speed);
    }
}
