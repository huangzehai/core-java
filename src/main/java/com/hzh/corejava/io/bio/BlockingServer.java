package com.hzh.corejava.io.bio;

import com.hzh.corejava.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingServer {
    private static boolean running = true;

    Logger logger = LoggerFactory.getLogger(BlockingServer.class);

    /**
     * Listing 1.1 Blocking I/O example
     */
    public void serve(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        logger.info("Server is started...");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        while (running) {
            Socket clientSocket = serverSocket.accept();
            logger.info("Get request");
            pool.submit(new Job(clientSocket));
        }
        pool.shutdown();
        serverSocket.close();
    }

    public void shutdown() {
        running = false;
    }

    public static void main(String[] args) throws IOException {
        BlockingServer server = new BlockingServer();
        server.serve(Constants.PORT);
    }
}
