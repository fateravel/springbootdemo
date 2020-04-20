package com.example.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengsong
 * @date 2020/4/19 1:41 下午
 */
public class ObserverPattern {

    public static void main(String[] args) {
        Target target = new TargetA();
        Observer observerA = new ObserverA();
        target.add(observerA);
        target.add(new ObserverB());
        target.notifyObserver();
        System.out.println("-------------");
        target.remove(observerA);
        target.notifyObserver();
    }
}

abstract class Target {
    protected List<Observer> observers = new ArrayList<>();
    protected void add(Observer observer) {
        observers.add(observer);
    }
    protected void remove(Observer observer) {
        observers.remove(observer);
    }

    protected abstract void notifyObserver();
}

class TargetA extends Target {
    @Override
    protected void notifyObserver() {
        System.out.println("A对象发生变化，需要通知观察者");
        observers.forEach(Observer::respond);
    }
}

interface Observer {
    void respond();
}

class ObserverA implements Observer {
    @Override
    public void respond() {
        System.out.println("ObserverA has reactive");
    }
}

class ObserverB implements Observer {
    @Override
    public void respond() {
        System.out.println("ObserverB has reactive");
    }
}
