package com.hzh.corejava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by huangzehai on 2017/3/14.
 */
public class SocketChannelNonBlockingMode {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(5000));
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()) {
            //wait, or do something else...
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Waiting...");
        }

        //Sent request.
        String requestBody = "How are you? Server" + System.getProperty("line.separator");
        socketChannel.write(ByteBuffer.wrap(requestBody.getBytes()));

        //Get response
        //There is problem for non-blocking read, considering Selector.
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int bytesRead = socketChannel.read(buffer);
        System.out.println(bytesRead);
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
