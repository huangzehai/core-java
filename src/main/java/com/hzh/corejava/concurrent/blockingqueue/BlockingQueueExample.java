package com.hzh.corejava.concurrent.blockingqueue;

/**
 * Created by lenovo on 2017/2/14.
 */
public class BlockingQueueExample {
    public static void main(String[] args) {
        IBlockingQueue<Integer> blockingQueue = new IBlockingQueue<>(10);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    blockingQueue.enqueue(i);
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
                    blockingQueue.dequeue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
