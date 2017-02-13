package com.hzh.corejava.concurrent.waitnotify;

import java.util.Date;

/**
 * Created by lenovo on 2017/2/13.
 */
public class Waiter implements Runnable {

    private Message msg;

    public Waiter(Message m) {
        this.msg = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println(name + " waiting to get notified at time: " + new Date());
                msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " waiter thread got notified at time: " + new Date());
            //process the message now
            System.out.println(name + " processed: " + msg.getMsg());
        }
    }

}