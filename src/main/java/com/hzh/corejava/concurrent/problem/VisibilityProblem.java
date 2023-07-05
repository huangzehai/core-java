package com.hzh.corejava.concurrent.problem;

/**
 *   Problem: 写线程将flag修改为false了，但main()一直没有退出，即读线程没有读取到修改后的flag，一直运行。
 */
public class VisibilityProblem {
    // 1. Create a shared field variable.
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        // 2. Create a thread continuous reading shared variable.
        new Thread(() -> {
            while (flag) {
                // Do not print flag, as print will change switch to main thread, and flush cache.
            }
        }).start();

        Thread.sleep(2000);

        // 3. Create a thread modifying the shared variable.
        new Thread(() -> {
            flag = false;
            System.out.println("Another thread have change flag as false.");
        }).start();
    }
}
