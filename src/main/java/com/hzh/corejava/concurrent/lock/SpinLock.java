package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock implements Lock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

}
