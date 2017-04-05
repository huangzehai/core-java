package com.hzh.corejava.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * Created by huangzehai on 2017/4/5.
 */
public class DelayQueueExample {
    public static void main(String[] args) {
        final BlockingQueue<DelayElement> queue = new DelayQueue<>();

        DelayQueueProducer queueProducer = new DelayQueueProducer(queue);
        new Thread(queueProducer).start();

        DelayQueueConsumer queueConsumer1 = new DelayQueueConsumer(queue);
        new Thread(queueConsumer1).start();

        DelayQueueConsumer queueConsumer2 = new DelayQueueConsumer(queue);
        new Thread(queueConsumer2).start();

    }
}