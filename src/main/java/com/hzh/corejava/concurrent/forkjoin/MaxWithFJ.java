package com.hzh.corejava.concurrent.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by huangzehai on 2017/2/25.
 */
public class MaxWithFJ extends RecursiveAction {
    private final int threshold;
    private final SelectMaxProblem problem;
    public int result;

    public MaxWithFJ(SelectMaxProblem problem, int threshold) {
        this.problem = problem;
        this.threshold = threshold;
    }

    protected void compute() {
        if (problem.size < threshold)
            result = problem.solveSequentially();
        else {
            int midpoint = problem.size / 2;
            MaxWithFJ left = new MaxWithFJ(problem.subProblem(0, midpoint), threshold);
            MaxWithFJ right = new MaxWithFJ(problem.subProblem(midpoint +
                    1, problem.size), threshold);
            invokeAll(left, right);
            result = Math.max(left.result, right.result);
        }
    }

    public static void main(String[] args) {
        int size = 10000000;
        int[] numbers = new int[size];
        Random random=new Random();
        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(size);
        }
        SelectMaxProblem problem = new SelectMaxProblem(numbers,0,size,size);
        int threshold = 100000;
        int nThreads = Runtime.getRuntime().availableProcessors();
        MaxWithFJ mfj = new MaxWithFJ(problem, threshold);
        ForkJoinPool fjPool = new ForkJoinPool(nThreads);

        fjPool.invoke(mfj);
        int result = mfj.result;
        System.out.println(result);
    }
}