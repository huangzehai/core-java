package com.hzh.corejava.concurrent.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Created by huangzehai on 2017/2/26.
 */
class SortTask extends RecursiveAction {
    private static final int THRESHOLD = 10;
    final long[] array;
    final int lo;
    final int hi;

    SortTask(long[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    protected void compute() {
        if (hi - lo < THRESHOLD)
            sequentiallySort(array, lo, hi);
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid),
                    new SortTask(array, mid, hi));
            merge(array, lo, hi);
        }
    }

    private void sequentiallySort(long[] array, int lo, int hi) {
        Arrays.sort(array, lo, hi);
    }

    private void merge(long[] array, int lo, int hi) {
        //TODO
    }
}