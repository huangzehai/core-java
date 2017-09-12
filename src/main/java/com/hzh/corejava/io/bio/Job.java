package com.hzh.corejava.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Job implements Runnable {
    private Socket clientSocket;

    public Job(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
