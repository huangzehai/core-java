package com.hzh.corejava.collection;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        Stream.of(1, 2, 3).map(num -> num * 2).collect(Collectors.toList());
    }
}
