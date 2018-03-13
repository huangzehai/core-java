package com.hzh.corejava.concurrent;

public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadLocal<String> name = new ThreadLocal<>();
        name.set("Kate");
        String localName = name.get();
        System.out.println(localName);
    }
}
