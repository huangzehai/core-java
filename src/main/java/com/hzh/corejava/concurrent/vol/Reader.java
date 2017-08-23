package com.hzh.corejava.concurrent.vol;

public class Reader implements Runnable {
    private Counter counter;

    public Reader(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        int index = 1000;
        while (index > 0) {
            System.out.println("[Reader] Counter is: " + counter.getCounter());
            index--;
        }
    }
}
