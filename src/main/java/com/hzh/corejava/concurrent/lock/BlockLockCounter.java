package com.hzh.corejava.concurrent.lock;

public class BlockLockCounter {

    private BlockLock lock = new BlockLock();
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
