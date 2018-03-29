package com.hzh.corejava.concurrent.forkjoin;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureExample {
    public static void main(String[] args) throws IOException {
        List<CompletableFuture<Void>> tasks = new ArrayList<>();


        List<String> urls = Files.readAllLines(Paths.get("urls.txt"));
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        urls.forEach(url -> tasks.add(CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Get: " + count.incrementAndGet() + " " + url);
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(url);
                // add request header
                request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
                HttpResponse response = client.execute(request);
                EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        })));

        System.out.println("Time spent: " + (System.currentTimeMillis() - start));

//        CompletableFuture.allOf(tasks);
    }
}
