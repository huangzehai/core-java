package com.hzh.corejava.io.bio;

import com.hzh.corejava.constant.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BlockingClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = Constants.PORT;
        Socket client = new Socket(hostName, portNumber);

        //Request
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println("How do you do?");

        //Response
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String fromServer;
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
        }

        out.close();
        in.close();
        client.close();
    }
}
