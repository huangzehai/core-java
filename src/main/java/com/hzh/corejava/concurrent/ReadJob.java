package com.hzh.corejava.concurrent;

/**
 * Created by Adam on 2016/2/23.
 */
public class ReadJob implements Runnable {
    private Counter counter;

    public ReadJob(Counter counter){
        this.counter=counter;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("The count is :"+counter.getCount());
        }
    }
}
