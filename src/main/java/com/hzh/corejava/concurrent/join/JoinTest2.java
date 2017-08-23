package com.hzh.corejava.concurrent.join;

/**
 * 问题：现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行？
 * Created by huangzehai on 2017/2/13.
 */
public class JoinTest2 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {

                try {
                    System.out.println("thread 1 running....");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("thread 1 stoped....");
                }
                super.run();
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("thread 2 running....");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("thread 2 stoped....");
                }
                super.run();
            }
        };
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("thread 3 running....");
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("thread 3 stoped....");
                }
                super.run();
            }
        };
        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
            thread3.start();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
