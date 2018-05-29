package com.hzh.corejava.concurrent.pool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 实现一个简单的固定大小的线程池
 */
public class FixedThreadPool {
    /**
     * 线程池大小
     */
    private final int nThreads;
    /**
     * 工作队列
     */
    private final PoolWorker[] threads;
    /**
     * 等待队列
     */
    private final LinkedBlockingQueue<Runnable> queue;

    public FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue<>();
        threads = new PoolWorker[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    /**
     *  提价任务
     * @param task
     */
    public void submit(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable task;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = queue.poll();
                }
                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }

}

