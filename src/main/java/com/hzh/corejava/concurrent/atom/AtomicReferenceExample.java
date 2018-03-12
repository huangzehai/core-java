package com.hzh.corejava.concurrent.atom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AtomicReferenceExample {


    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(2);
        int nThreads = 100;
        AccessStatisticsProcessor processor = new AccessStatisticsProcessor();
        for (int index = 0; index < nThreads; index++) {
            pool.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                processor.incrementPageCount(true);
            });
        }
        pool.shutdown();
        try {
            pool.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(processor.getStats());

    }


}
