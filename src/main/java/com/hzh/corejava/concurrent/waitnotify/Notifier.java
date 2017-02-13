package com.hzh.corejava.concurrent.waitnotify;

/**
 * Created by lenovo on 2017/2/13.
 */
public class Notifier implements Runnable {

    private Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                System.out.println("Notify");
//                msg.setMsg(name+" Notifier work done");
                msg.notify();
                msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}