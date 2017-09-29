package com.hzh.corejava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadFailTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new Job1());
        pool.submit(new Job2());
        pool.shutdown();
    }
}


class Job1 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Job 1 is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Job2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Job 2 is running");
            throw new NullPointerException();
        }
    }
}