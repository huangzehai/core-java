package com.hzh.corejava.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by huangzehai on 2017/5/9.
 */
public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String value = it.next();
            if ("hello".equals(value)) {
                it.remove();
            }

        }

       list.forEach(System.out::println);

    }
}
