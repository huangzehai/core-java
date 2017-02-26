package com.hzh.corejava.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by huangzehai on 2017/2/26.
 */
public class ApplyerExample {
    public static void main(String[] args) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        double[] numbers = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        double result = sumOfSquares(pool, numbers);
        System.out.println(result);
    }

    private static double sumOfSquares(ForkJoinPool pool, double[] array) {
        int n = array.length;
        Applyer a = new Applyer(array, 0, n, null);
        pool.invoke(a);
        return a.result;
    }
}
