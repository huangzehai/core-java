package com.hzh.corejava.concurrent.synchronnized;

/**
 * Created by huangzehai on 2017/2/15.
 */
public class SynchronizedCounterExample {
    public static void main(String[] args) {
        SynchronizedCounter counter = new SynchronizedCounter();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();
    }
}
