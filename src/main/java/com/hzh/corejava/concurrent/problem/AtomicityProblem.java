package com.hzh.corejava.concurrent.problem;

import java.util.ArrayList;
import java.util.List;

public class AtomicityProblem {
    private static int number = 0;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                number++;
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable);
            thread.setName("Thread-" + i);
            thread.start();
            threads.add(thread);
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("number: " + number);
    }
}
