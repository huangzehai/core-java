package com.hzh.corejava.concurrent.vol;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final int JOBS = 5;


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        Job job = new Job();
        pool.submit(() -> {
            job.run();
        });

        //Let Server running for a while.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //shutdown by another thread.
        pool.submit(() -> {
            job.shutdown();
            System.out.println("shutdown");
        });

        pool.shutdown();

        try {
            pool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
