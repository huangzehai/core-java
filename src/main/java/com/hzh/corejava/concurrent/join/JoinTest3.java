package com.hzh.corejava.concurrent.join;

/**
 * Created by huangzehai on 2017/2/13.
 */
public class JoinTest3 {
    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> System.out.println("t1"));
        final Thread t2 = new Thread(() -> {
            try {
                //引用t1线程，等待t1线程执行完
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        Thread t3 = new Thread(() -> {
            try {
                //引用t2线程，等待t2线程执行完
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        });


        Thread t4 = new Thread(new Deamon(t3));
        t4.start();
        t3.start();
        t2.start();
        t1.start();
    }
}

class Deamon implements Runnable {
    private Thread thread;

    Deamon(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        //Do something.
        System.out.println("Running Thread 4");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Do something.
        System.out.println("End Thread 4");
    }

}