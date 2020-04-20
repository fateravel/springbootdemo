package com.example.design;

/**
 * @author pengsong
 * @date 2020/4/20 7:07 下午
 */
public class AdapterPattern {

    public static void main(String[] args) {
        Target target = new Adapter(new ActualTarget());
        target.request();;
    }

    interface Target {
        void request();
    }

    static class ActualTarget {
        public void myRequest() {
            System.out.println("实际需要执行的方法被执行");
        }
    }

    /*static class Adapter extends ActualTarget implements Target {

        @Override
        public void request() {
            this.myRequest();
        }
    }*/

    static class Adapter implements Target {

        private ActualTarget actualTarget;

        public Adapter(ActualTarget actualTarget) {
            this.actualTarget = actualTarget;
        }

        @Override
        public void request() {
            actualTarget.myRequest();
        }
    }
}