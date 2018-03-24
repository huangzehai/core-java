package com.hzh.corejava.concurrent;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        int sum = IntStream.range(1, 10000).parallel().sum();
        System.out.println(sum);
        Stream.of(1,2,3);
    }
}
