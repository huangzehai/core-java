package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread start");
            System.out.println("Request resource");
            LockSupport.park();
            System.out.println("Thread end");
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("Release resource");
        LockSupport.unpark(thread);
    }
}
