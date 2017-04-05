package com.hzh.corejava.jvm;

import java.lang.ref.WeakReference;

/**
 * Created by huangzehai on 2017/3/25.
 */
public class StringExample {
    public static void main(String[] args) {
        System.out.println("foo" == "foo");
        System.out.println(new String("bar") == new String("bar"));

        String textLiteral = "Hi";
        String text = new String("hello");
        WeakReference<String> weakTextLiteral = new WeakReference<>(textLiteral);
        WeakReference<String> weakText = new WeakReference<>(text);
        System.gc();
        System.out.println("Before gc: string literal is " + weakTextLiteral.get() + " string object is " + weakText.get());
        textLiteral = null;
        text = null;
        System.gc();
        System.out.println("After gc: string literal is " + weakTextLiteral.get() + " string object is " + weakText.get());
    }
}