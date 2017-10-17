package com.hzh.corejava.function;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        testReduce();
    }

    private static void testReduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        String text = list.stream().map(a -> Integer.toString(a)).reduce((a, b) -> a + "," + b).get();
        System.out.println(text);
    }
}
