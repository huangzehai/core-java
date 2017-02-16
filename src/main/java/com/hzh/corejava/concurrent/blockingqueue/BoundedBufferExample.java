package com.hzh.corejava.concurrent.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by huangzehai on 2017/2/16.
 */
public class BoundedBufferExample {
    public static void main(String[] args) {
        BoundedBuffer blockingQueue = new BoundedBuffer();
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
                    int value = (int) blockingQueue.take();
                    System.out.println(value);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        System.out.println(blockingQueue.getItems());
    }
}
