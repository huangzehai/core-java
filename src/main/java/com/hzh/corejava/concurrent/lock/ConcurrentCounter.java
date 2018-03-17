package com.hzh.corejava.concurrent.lock;

public class ConcurrentCounter {

    private Lock lock = new BlockedCLHLock();
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
