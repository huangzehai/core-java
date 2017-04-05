package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huangzehai on 2017/4/5.
 */
public class ReentrantCounter {
    private Lock lock = new ReentrantLock();
    private int value = 0;

    public void increase() {
        lock.lock();
        value++;
        logging();
        lock.unlock();
    }

    private void logging() {
        lock.lock();
        System.out.println("Current value is " + value);
        lock.unlock();
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        ReentrantCounter counter = new ReentrantCounter();

        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            pool.submit(() -> {
                counter.increase();
            });
        }

        pool.shutdown();

        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Total is " + counter.getValue());
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }
}
