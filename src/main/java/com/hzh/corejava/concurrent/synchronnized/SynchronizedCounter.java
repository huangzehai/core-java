package com.hzh.corejava.concurrent.synchronnized;

/**
 * Created by huangzehai on 2017/2/15.
 */
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        System.out.println("Start increment");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c++;
        System.out.println("Done increment");
    }

    public synchronized void decrement() {
        System.out.println("decrement");
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
