package com.hzh.corejava.concurrent;

import java.util.Date;

public class Counter {
    private int count;

    public synchronized void increaseCount() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ":increaseCount" + new Date());
        count++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }

    public synchronized void doubleIncreaseCount() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ":double increaseCount" + new Date());
        count += 2;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
