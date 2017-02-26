package com.hzh.corejava.concurrent.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by huangzehai on 2017/2/26.
 */
class IncrementTask extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    final long[] array; final int lo; final int hi;

    public long[] getArray() {
        return array;
    }

    IncrementTask(long[] array, int lo, int hi) {
        this.array = array; this.lo = lo; this.hi = hi;
    }
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            for (int i = lo; i < hi; ++i)
                array[i]++;
        }
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new IncrementTask(array, lo, mid),
                    new IncrementTask(array, mid, hi));
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int size = 10000000;
        long[] numbers = new long[size];
        for(int i=0;i<size;i++){
            numbers[i]= random.nextInt(size);
        }
//        for(long number:numbers){
//            System.out.println(number);
//        }
//        System.out.println("==============================");
        IncrementTask task = new IncrementTask(numbers,0,size);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
//        for(long number:task.getArray()){
//            System.out.println(number);
//        }
    }
}
