package com.hzh.corejava.nio.selector;

import com.hzh.corejava.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author Crunchify.com
 */

public class NioClient {

    private static Logger logger = LoggerFactory.getLogger(NioServer.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        InetSocketAddress socketAddress = new InetSocketAddress("localhost", Constants.PORT);
        SocketChannel client = SocketChannel.open(socketAddress);

        logger.info("Connecting to Server on port {}", Constants.PORT);

        sentRequest(client);

        //发送完成后读取响应
        processResponse(client);

        client.close();
    }

    private static void sentRequest(SocketChannel client) throws IOException, InterruptedException {
        ArrayList<String> companyDetails = new ArrayList<>();

        // create a ArrayList with companyName list
        companyDetails.add("Facebook");
        companyDetails.add("Twitter");
        companyDetails.add("IBM");
        companyDetails.add("Google");
        companyDetails.add("End");

        for (String companyName : companyDetails) {
            byte[] message = new String(companyName).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);

            logger.info("sending: " + companyName);
            buffer.clear();

            // wait for 2 seconds before sending next message
            Thread.sleep(1000);
        }
    }

    private static void processResponse(SocketChannel client) throws IOException {
        //Get response
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = client.read(buffer);
        if (bytesRead != Constants.END_OF_FILE) {
            buffer.flip();
            byte[] lineBytes = new byte[bytesRead];
            buffer.get(lineBytes, 0, bytesRead);
            String line = new String(lineBytes);
            logger.info(line);
        }
    }

}
