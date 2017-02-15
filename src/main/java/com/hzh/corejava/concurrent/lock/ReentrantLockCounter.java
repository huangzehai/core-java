package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huangzehai on 2017/2/15.
 */
public class ReentrantLockCounter {
    private Lock lock = new ReentrantLock();
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increase() {
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                Thread.sleep(1);
                value++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //release lock
            lock.unlock();
        }
    }

    public void doLogging() {
        //logging, no need for thread safety
        System.out.println("logging");
    }
}
