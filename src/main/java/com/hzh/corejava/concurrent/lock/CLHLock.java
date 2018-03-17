package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock implements Lock {
    public static class CLHNode {
        //该字段若为true表示该线程需要获取锁，且不释放锁，为false表示线程释放了锁。
        private volatile boolean isLocked = true;
    }

    @SuppressWarnings("unused")
    /**
     * 线程锁队列尾部，隐式队列
     */
    private volatile CLHNode tail;

    /**
     * 当前线程锁状态
     */
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<>();

    /**
     * 线程尾部更新器
     */
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        //当前线程请求所，isLocked 默认值已设置为true
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        //队列尾部的锁为当前线程依赖的锁
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            while (preNode.isLocked) {
            }
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock() {
        CLHNode node = LOCAL.get();
        //如果队列的尾部是当前线程，则将尾部置为null；如果队列的尾部是后面的其他线程，则将更新当前线程的锁状态
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        node = null;
    }
}
