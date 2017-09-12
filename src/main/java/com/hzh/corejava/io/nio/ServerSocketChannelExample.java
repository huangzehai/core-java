package com.hzh.corejava.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by lenovo on 2017/3/14.
 */
public class ServerSocketChannelExample extends Thread {
    private ServerSocketChannel channel;

    public static void main(String[] args) {
        ServerSocketChannelExample server = new ServerSocketChannelExample();
        server.startServer();
    }

    public void startServer() {
        this.start();
    }

    @Override
    public void run() {
        try {
            channel = ServerSocketChannel.open().bind(new InetSocketAddress(5000));
            while (true) {
                SocketChannel socketChannel = channel.accept();
                ByteBuffer buffer = ByteBuffer.allocate(48);


                int bytesRead = socketChannel.read(buffer);
                //
                while (bytesRead != -1) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.print((char) buffer.get());
                    }
                    socketChannel.write(ByteBuffer.wrap("Got it".getBytes()));
                    buffer.clear();
                    bytesRead = socketChannel.read(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
