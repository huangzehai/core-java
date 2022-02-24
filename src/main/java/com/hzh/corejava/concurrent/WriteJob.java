package com.hzh.corejava.concurrent;

/**
 * Created by Adam on 2016/2/23.
 */
public class WriteJob implements  Runnable{
    private Counter counter;
    public WriteJob(Counter counter){
        this.counter=counter;
    }
    @Override
    public void run() {
        for(int i =0;i<10;i++){
            counter.increaseCount();
        }
    }
}
