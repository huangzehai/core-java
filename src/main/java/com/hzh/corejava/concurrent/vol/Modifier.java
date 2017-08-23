package com.hzh.corejava.concurrent.vol;

public class Modifier implements Runnable {

    private Counter counter;

    public Modifier(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int index = 0; index < 1000; index++) {
            counter.increase();
            System.out.println("[Modifier] Counter is changed to :" + counter);
        }
    }
}
