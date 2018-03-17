package com.hzh.corejava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalExample {


    private ThreadLocal<String> threadName;


    public ThreadLocalExample() {
        threadName = ThreadLocal.withInitial(() -> Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample example = new ThreadLocalExample();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int nThreads = 4;
        for (int i = 0; i < nThreads; i++) {
            pool.submit(() -> System.out.println(example.getThreadName().get()));
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("end");
    }

    public ThreadLocal<String> getThreadName() {
        return threadName;
    }
}
