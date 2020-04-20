package com.example.design;

/**
 * @author pengsong
 * @date 2020/4/20 10:13 下午
 */
public class DecoratorPattern {

    public static void main(String[] args) {
        Target decoratorTarget = new ExtDecoratorTarget(new DefaultTarget());
        decoratorTarget.method();

    }

    interface Target {
        void method();
    }

    static class DefaultTarget implements Target{
        @Override
        public void method() {
            System.out.println("默认的原始实例方法");
        }
    }

    static class DecoratorTarget implements Target {
        private Target target;

        public DecoratorTarget(Target target) {
            this.target = target;
        }

        @Override
        public void method() {
            target.method();
            System.out.println("装饰着对象方法代替目标对象执行");
        }
    }

    static class ExtDecoratorTarget extends DecoratorTarget {

        public ExtDecoratorTarget(Target target) {
            super(target);
        }

        public void method() {
            super.method();
            extraMethod();
        }
        private void extraMethod() {
            System.out.println("装饰着额外的操作");
        }
    }
}
