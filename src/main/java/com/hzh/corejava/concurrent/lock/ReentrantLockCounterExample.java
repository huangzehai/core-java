package com.hzh.corejava.concurrent.lock;

/**
 * Created by huangzehai on 2017/2/15.
 */
public class ReentrantLockCounterExample {
    public static void main(String[] args) {
        ReentrantLockCounter counter = new ReentrantLockCounter();
        CountJob job = new CountJob(counter);
        CountJob job2 = new CountJob(counter);
        Thread thread = new Thread(job);
        thread.start();

        Thread thread2 = new Thread(job2);
        thread2.start();

        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getValue());
    }

}
