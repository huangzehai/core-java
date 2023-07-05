package com.hzh.corejava.concurrent.synchronnized;

public class SynchronizedReentrantExample {
    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        thread1.start();
        thread2.start();

        Thread thread3 = new AnotherThread();
        Thread thread4 = new AnotherThread();
        thread3.start();
        thread4.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        synchronized (MyThread.class) {
            System.out.println(getName() + " entry first synchronized block");
            synchronized (MyThread.class) {
                System.out.println(getName() + " entry second synchronized block");
            }
        }
    }
}

class AnotherThread extends Thread {
    @Override
    public void run() {
        test1();
    }

    private void test1() {
        synchronized (AnotherThread.class) {
            System.out.println(getName() + " entry first synchronized block");
            test2();
        }
    }

    private synchronized void test2() {
        synchronized (AnotherThread.class) {
            System.out.println(getName() + " entry second synchronized block");
        }
    }
}