package com.hzh.corejava.concurrent.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lenovo on 2017/2/14.
 */
public class BlockingQueueExample2 {
    public static void main(String[] args) {
        java.util.concurrent.BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    blockingQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                //Let thread 1 run first.
                Thread.sleep(100);
                for (int i = 0; i < 20; i++) {
                    blockingQueue.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
