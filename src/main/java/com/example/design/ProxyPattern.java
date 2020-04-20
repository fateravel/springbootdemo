package com.example.design;

/**
 * @author pengsong
 * @date 2020/4/20 9:20 下午
 */
public class ProxyPattern {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.method();
    }

    interface Target {
        void method();
    }

    static class RealTarget implements Target {
        @Override
        public void method() {
            System.out.println("被代理对象方法被执行");
        }
    }

    static class Proxy implements Target{
        private RealTarget realTarget;

        public Proxy() {
            this.realTarget = new RealTarget();
        }

        @Override
        public void method() {
            preHandler();
            realTarget.method();
            afterHandler();
        }

        private void preHandler() {
            System.out.println("代理对象前置处理");
        }

        private void afterHandler() {
            System.out.println("代理对象后置置处理");
        }
    }
}
