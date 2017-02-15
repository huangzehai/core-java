package com.hzh.corejava.concurrent.lock;

/**
 * Created by huangzehai on 2017/2/15.
 */
public class CountJob implements Runnable {

    private ReentrantLockCounter counter;


    public CountJob(ReentrantLockCounter atomCounter) {
        this.counter = atomCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            counter.increase();
        }
        counter.doLogging();
    }
}
