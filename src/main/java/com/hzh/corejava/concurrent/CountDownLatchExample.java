package com.hzh.corejava.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by huangzehai on 2017/2/20.
 */
public class CountDownLatchExample { // ...
    private static final int N = 3;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and start threads
            new Thread(new Worker(startSignal, doneSignal)).start();

        doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
        doWhenJobDone();
    }

    private static void doSomethingElse(){
        System.out.println("Do something else.");
    }

    private static void doWhenJobDone(){
        System.out.println("All jobs were done.");
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() {
        System.out.println("Do work");
    }
}
