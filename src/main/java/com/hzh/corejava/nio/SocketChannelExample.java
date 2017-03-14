package com.hzh.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by lenovo on 2017/3/14.
 */
public class SocketChannelExample {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(5000));
        socketChannel.configureBlocking(true);
        //Sent request.
        String requestBody = "How are you? Server";
        socketChannel.write(ByteBuffer.wrap(requestBody.getBytes()));

        //Get response
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead != -1) {
            buffer.flip();
            byte[] lineBytes = new byte[bytesRead];
            buffer.get(lineBytes, 0, bytesRead);
            String line = new String(lineBytes);
            System.out.println(line);
        }
        socketChannel.close();
    }
}
