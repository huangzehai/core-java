package com.hzh.corejava.concurrent.blockingqueue;

import java.util.LinkedList;
import java.util.List;

public class IBlockingQueue<T> {
    private List<T> queue = new LinkedList<>();
    private int limit = 10;

    public IBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(T item)
            throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }

        if (this.queue.size() == 0) {
            notifyAll();
        }
        System.out.println("Add " + item);
        this.queue.add(item);
    }


    public synchronized T dequeue()
            throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }

        if (this.queue.size() == this.limit) {
            notifyAll();
        }

        System.out.println("Remove item");
        return this.queue.remove(0);
    }

}
