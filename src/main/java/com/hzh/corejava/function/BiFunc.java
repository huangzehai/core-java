package com.hzh.corejava.function;

@FunctionalInterface
public interface BiFunc<T, U, R> {
    R apply(T t, U u);
}
