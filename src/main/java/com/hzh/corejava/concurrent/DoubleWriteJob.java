package com.hzh.corejava.concurrent;

/**
 * Created by Adam on 2016/2/23.
 */
public class DoubleWriteJob implements Runnable {
    private Counter counter;

    public DoubleWriteJob(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.doubleIncreaseCount();
        }
    }
}
