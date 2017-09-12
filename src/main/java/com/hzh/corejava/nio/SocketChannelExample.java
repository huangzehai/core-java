package com.hzh.corejava.nio;

import com.hzh.corejava.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by lenovo on 2017/3/14.
 */
public class SocketChannelExample {
    private Logger logger = LoggerFactory.getLogger(SocketChannelExample.class);

    public static void main(String[] args) throws IOException {
        SocketChannelExample client = new SocketChannelExample();
        client.sendRequest();
    }

    private void sendRequest() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(Constants.PORT));
        socketChannel.configureBlocking(true);
        //Sent request.
        String requestBody = "How are you? Server";
        socketChannel.write(ByteBuffer.wrap(requestBody.getBytes()));

        //Get response
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead != Constants.END_OF_FILE) {
            buffer.flip();
            byte[] lineBytes = new byte[bytesRead];
            buffer.get(lineBytes, 0, bytesRead);
            String line = new String(lineBytes);
            logger.info(line);
        }
        socketChannel.close();
    }
}
