package com.hzh.corejava.concurrent.synchronnized;

/**
 * Created by huangzehai on 2017/2/15.
 */
public class SynchronizedCounter {
    private int counter = 0;
    private Object lock  = new Object();

    public void increment() {
        System.out.println("Start increment");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            counter++;
        }
        System.out.println("Done increment");
    }

    public synchronized void decrement() {
        System.out.println("decrement");
        counter--;
    }

    public synchronized int value() {
        return counter;
    }
}
