package com.hzh.corejava.jvm;

/**
 * 无限递归将抛出StackOverflowError
 */
public class StackOverFlowErrorExample {
    public static void main(String[] args) {
        doSomeThing();
    }

    private static void doSomeThing() {
        doSomeThing();
    }
}
