package com.hzh.corejava.concurrent.synchronnized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InterrcuptableExample {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        testInterrupted();
    }

    public static void testInterrupted() throws InterruptedException {
        Runnable run = () -> {
            String name = Thread.currentThread().getName();
            boolean b = false;
            try {
                b = lock.tryLock(2, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + " got lock");
                    Thread.sleep(10000);
                } else {
                    System.out.println(name + " can't get lock");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    lock.unlock();
                    System.out.println(name+" unlock");
                }
            }
        };

        Thread t1 = new Thread(run);
        t1.setName("Thread 1");
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run);
        t2.setName("Thread 2");
        t2.start();

//        System.out.println("Before interrupt thread 2");
//        t2.interrupt();
//        System.out.println("After interrupt thread 2");
//        Thread.sleep(1000);
//        System.out.println("thread 1 state: " + t1.getState());
//        System.out.println("thread 2 state: " + t2.getState());
    }
}
