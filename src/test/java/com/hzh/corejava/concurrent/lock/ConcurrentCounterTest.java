package com.hzh.corejava.concurrent.lock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentCounterTest {
    @Test
    public void increase() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        int nThreads = 1000;
        ConcurrentCounter counter = new ConcurrentCounter();
        for (int i = 0; i < nThreads; i++) {
            pool.submit(() -> counter.increase());
        }
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        Assert.assertEquals(1000, counter.getValue());
    }

}