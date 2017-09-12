package com.hzh.corejava.io.bio;

import com.hzh.corejava.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServer {

    Logger logger = LoggerFactory.getLogger(BlockingServer.class);

    /**
     * Listing 1.1 Blocking I/O example
     */
    public void serve(int portNumber) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        logger.info("Server is started...");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            logger.info("Get request");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //Process request
            String request;
            if ((request = in.readLine()) != null) {
                System.out.println(request);
            }


            //Response
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("got it");

            in.close();
            out.close();
            clientSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        BlockingServer server = new BlockingServer();
        server.serve(Constants.PORT);
    }
}
