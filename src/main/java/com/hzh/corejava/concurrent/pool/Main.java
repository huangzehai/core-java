package com.hzh.corejava.concurrent.pool;

public class Main {
    public static void main(String[] args) {
        FixedThreadPool pool = new FixedThreadPool(7);
        for (int i = 0; i < 5; i++) {
            Task task = new Task(i);
            pool.submit(task);
        }
    }
}
