package com.hzh.corejava.concurrent.lock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SpinLockCounterTest {
    @Test
    public void increase() throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        int nThreads = 100;
        SpinLockCounter counter = new SpinLockCounter();
        for (int i = 0; i < nThreads; i++) {
            pool.submit(() -> {
                counter.increase();
            });
        }
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        Assert.assertEquals(100, counter.getValue());
    }

}