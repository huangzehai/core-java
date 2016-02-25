package com.hzh.corejava.concurrent;

import java.io.IOException;

/**
 * Created by Adam on 2016/2/25.
 */
public class SimpleServer {
    public static void main(String[] args) {
        try {
            Thread thread =new Thread(new NetworkService(8080,3));
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
