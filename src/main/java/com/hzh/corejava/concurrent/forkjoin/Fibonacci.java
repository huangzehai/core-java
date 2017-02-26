package com.hzh.corejava.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by huangzehai on 2017/2/26.
 */
class Fibonacci extends RecursiveTask<Integer> {
    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    protected Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        Fibonacci task = new Fibonacci(10);
        int nThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool fjPool = new ForkJoinPool(nThreads);
        Integer result = fjPool.invoke(task);
        System.out.println(result);
    }
}
