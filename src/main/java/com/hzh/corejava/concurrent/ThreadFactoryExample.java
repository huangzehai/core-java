package com.hzh.corejava.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by huangzehai on 2017/2/17.
 */
public class ThreadFactoryExample {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Thread thread = threadFactory.newThread(() -> {
            System.out.println("Do something.");
        });
        thread.start();
    }
}
