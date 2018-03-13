package com.hzh.corejava.concurrent.lock;

public class SpinLockCounter {

    private SpinLock lock = new SpinLock();
    private int value = 0;

    public void increase() {
        lock.lock();
        value++;
        lock.unlock();
    }

    public int getValue() {
        return value;
    }
}
