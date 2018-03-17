package com.hzh.corejava.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock2 implements Lock {

    class QNode {
        //该字段若为true表示该线程需要获取锁，且不释放锁，为false表示线程释放了锁。
        boolean locked;
    }

    //用来实现隐式队列
    AtomicReference<QNode> tail;
    //前面线程锁状态
    ThreadLocal<QNode> myPred;
    //当前线程锁状态
    ThreadLocal<QNode> myNode;

    public CLHLock2() {
        tail = new AtomicReference<>(new QNode());
        myNode = ThreadLocal.withInitial(() -> new QNode());
        myPred = ThreadLocal.withInitial(() -> null);
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
        //当前线程请求获得锁
        qnode.locked = true;
        //将当前线程的myNode节点设置到对列尾,即后面的线程依赖于当前线程的锁状态
        QNode pred = tail.getAndSet(qnode);
        //当前线程依赖于队列尾线程的锁状态
        myPred.set(pred);
        while (pred.locked) {
        }
    }

    @Override
    public void unlock() {
        //设置当前线程的锁状态为：解锁
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }
}
