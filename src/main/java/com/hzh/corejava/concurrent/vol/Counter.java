package com.hzh.corejava.concurrent.vol;

public class Counter {
    private volatile int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increase() {
        this.counter = counter + 1;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counter=" + counter +
                '}';
    }
}
