package com.hzh.corejava.concurrent.completablefuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    private static final Logger logger = LoggerFactory.getLogger(CompletableFutureExample.class);

    public static void main(String[] args) {

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
}
