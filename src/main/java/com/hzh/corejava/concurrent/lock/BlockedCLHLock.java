package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/**
 * 阻塞锁
 */
public class BlockedCLHLock implements Lock {

    public static class CLHNode {
        /**
         * 被当前线程阻塞的后续线程
         */
        private volatile Thread isLocked;
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;

    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<>();

    private static final AtomicReferenceFieldUpdater<BlockedCLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(BlockedCLHLock.class, CLHNode.class, "tail");

    public void lock() {
        CLHNode node = new CLHNode();
        LOCAL.set(node);
        //获取队列尾部的线程，并将当前线程设置为队列尾部
        CLHNode preNode = UPDATER.getAndSet(this, node);

        if (preNode != null) {
            //队列尾部不为空，说明前面的线程正在运行。
            //当前线程被它前面的线程阻塞
            preNode.isLocked = Thread.currentThread();
            //阻塞当前线程
            LockSupport.park(this);
            preNode = null;
            LOCAL.set(node);
        }
    }

    public void unlock() {
        CLHNode node = LOCAL.get();
        //如果线程队列的尾部是当初线程，将尾部设置为null；否则说明还有后续线程，激活被当前线程阻塞的后续线程
        if (!UPDATER.compareAndSet(this, node, null)) {
            System.out.println(Thread.currentThread().getName() + " unlock\t" + node.isLocked.getName());
            LockSupport.unpark(node.isLocked);
        }
        node = null;
    }
}
