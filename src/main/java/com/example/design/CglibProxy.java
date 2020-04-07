package com.example.design;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author pengsong
 * @date 2020/4/7 2:15 下午
 */
public class CglibProxy<T> implements MethodInterceptor {

    private T target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("cglib动态代理前置处理--------->");
        Object invoke = method.invoke(target, args);
        System.out.println("cgib动态代理后置处理->>>>>>>>>>>>>>");
        return invoke;
    }

    public T getProxy(T target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T)enhancer.create();
    }

    public static void main(String[] args) {
        //IDog dog = new GunDog();
        //CglibProxy<GunDog> cglibProxy = new CglibProxy<>();
        GunDog proxy = new CglibProxy<GunDog>().getProxy(new GunDog());
        proxy.run(233);
    }
}
