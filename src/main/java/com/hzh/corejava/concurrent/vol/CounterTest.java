package com.hzh.corejava.concurrent.vol;

public class CounterTest {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread modifier = new Thread(new Modifier(counter));
        modifier.start();

        Thread reader  = new Thread(new Reader(counter));
        reader.start();
    }
}
