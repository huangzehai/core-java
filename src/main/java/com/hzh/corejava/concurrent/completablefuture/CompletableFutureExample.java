package com.hzh.corejava.concurrent.completablefuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {
    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureExample.class);

    public static void main(String[] args) {

//        runAsync();
//
//        completedFutureExample();
        completeExceptionallyExample();
    }

    private static void runAsync() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("Download an image from a remove website");
        }).thenRunAsync(() -> {
            logger.info("Render image");
        });

        logger.info("Do something else.");
        cf.join();
        logger.info("end");
    }

    static void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message");
        logger.info("Is done:" + cf.isDone());
        logger.info("value now:" + cf.getNow(null));
    }

    static void completeExceptionallyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(text -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return text.toUpperCase();
            throw new NullPointerException("Test");
        });
        CompletableFuture exceptionHandler = cf.handle((s, exception) -> {
            System.out.println(s);
            logger.error(exception.toString());
            return "Have handled exception";
        });
//        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        System.out.println(cf.isCompletedExceptionally());
        try {
            cf.join();
        } catch (CompletionException ex) { // just for testing
            ex.printStackTrace();
        }
        Object result = exceptionHandler.join();
        System.out.println(result);
    }
}
