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

        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", Constants.PORT);
        SocketChannel client = SocketChannel.open(crunchifyAddr);

        logger.info("Connecting to Server on port {}", Constants.PORT);

        ArrayList<String> companyDetails = new ArrayList<>();

        // create a ArrayList with companyName list
        companyDetails.add("Facebook");
        companyDetails.add("Twitter");
        companyDetails.add("IBM");
        companyDetails.add("Google");
        companyDetails.add("Tencent");

        for (String companyName : companyDetails) {
            byte[] message = new String(companyName).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);

            logger.info("sending: " + companyName);
            buffer.clear();

            // wait for 2 seconds before sending next message
            Thread.sleep(1000);
        }
        client.close();
    }

}
