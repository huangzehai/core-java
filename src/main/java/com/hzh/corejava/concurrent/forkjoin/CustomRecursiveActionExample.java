package com.hzh.corejava.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class CustomRecursiveActionExample {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        String text  = "hello world would you please go home";
        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction(text);
        forkJoinPool.execute(customRecursiveAction);
        customRecursiveAction.join();
    }
}
