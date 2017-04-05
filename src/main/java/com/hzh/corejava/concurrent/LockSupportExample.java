package com.hzh.corejava.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by huangzehai on 2017/4/5.
 */
public class LockSupportExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                System.out.println("start");
                long start = System.currentTimeMillis();
                long end = 0;
                while ((end - start) <= 1000) {
                    count++;
                    end = System.currentTimeMillis();
                }
                System.out.println("after 1 second.count=" + count);
                //等待或许许可
                LockSupport.park();
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

            }
        });
        t.start();
        Thread.sleep(2000);
        // 中断线程
        t.interrupt();
        System.out.println("main over");
    }
}
