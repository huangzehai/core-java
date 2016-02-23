package com.hzh.corejava.concurrent;

/**
 * Created by Adam on 2016/2/23.
 */
public class ConcurrentCount {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread writeJob = new Thread(new WriteJob(counter), "Write Job");
        Thread doubleWriteJob=new Thread(new DoubleWriteJob(counter),"Double Write Job");
        Thread readJob = new Thread(new ReadJob(counter), "Read Job");
        writeJob.start();
        doubleWriteJob.start();
        readJob.start();

        try {
            writeJob.join();
            doubleWriteJob.join();
//            readJob.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.getCount());
    }
}
