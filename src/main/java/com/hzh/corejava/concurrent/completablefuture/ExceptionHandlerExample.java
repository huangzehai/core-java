package com.hzh.corejava.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ExceptionHandlerExample {
    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            throw new NullPointerException("Mock error");
        }).handle((result, error) -> {
            System.out.println("Handle error");
            error.printStackTrace();
            return result;
        });

        CompletableFuture.runAsync(() -> {
            throw new NullPointerException("Second error");
        }).exceptionally(throwable -> {
            throwable.printStackTrace();
            System.out.println("process exception");
            return null;
        });
    }
}
