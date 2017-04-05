package com.hzh.corejava.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by huangzehai on 2017/3/25.
 */
public class WeakHashMapTest {
    public static void main(String[] args) {
        Map hashMap = new HashMap();

        Map weakHashMap = new WeakHashMap();

        String keyHashMap = new String("keyHashMap");
        String keyWeakHashMap = new String("keyWeakHashMap");

        hashMap.put(keyHashMap, "Ankita");
        weakHashMap.put(keyWeakHashMap, "Atul");
        System.gc();
        System.out.println("Before: hash map value:" + hashMap.get("keyHashMap") + " and weak hash map value:" + weakHashMap.get("keyWeakHashMap"));

        keyHashMap = null;
        keyWeakHashMap = null;

        System.gc();

        System.out.println("After: hash map value:" + hashMap.get("keyHashMap") + " and weak hash map value:" + weakHashMap.get("keyWeakHashMap"));
    }
}