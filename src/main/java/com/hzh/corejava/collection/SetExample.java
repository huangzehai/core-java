package com.hzh.corejava.collection;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SetExample {
    public static void main(String[] args) {
//        set();
        map();
        AtomicInteger i;

    }

    private static void set() {
        Set<String> set = new LinkedHashSet<>();
        set.add("d");
        set.add("b");
        set.add("c");
        set.add("a");
        set.add("h");
        set.add("b");
        set.forEach(System.out::println);
    }

    private static void map() {
        Map<String, String> map = new Hashtable<>();
//        map.put(null, "a");
        map.put("b", null);
    }
}
