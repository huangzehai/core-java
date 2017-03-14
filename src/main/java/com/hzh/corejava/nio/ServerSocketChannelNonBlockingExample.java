package com.hzh.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by lenovo on 2017/3/14.
 */
public class ServerSocketChannelNonBlockingExample extends Thread {
    private ServerSocketChannel channel;

    public static void main(String[] args) {
        ServerSocketChannelNonBlockingExample server = new ServerSocketChannelNonBlockingExample();
        server.startServer();
    }

    public void startServer() {
        this.start();
    }

    @Override
    public void run() {
        try {
            channel = ServerSocketChannel.open().bind(new InetSocketAddress(5000));
            channel.configureBlocking(false);
            //在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。 因此，需要检查返回的SocketChannel是否是null
            while (true) {
                SocketChannel socketChannel = channel.accept();
                if (socketChannel != null) {
                    ByteBuffer buffer = ByteBuffer.allocate(48);
                    int bytesRead = socketChannel.read(buffer);
                    //
                    while (bytesRead != -1) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            System.out.print((char) buffer.get());
                        }
                        buffer.clear();
                        bytesRead = socketChannel.read(buffer);
                    }
                    socketChannel.write(ByteBuffer.wrap("Got it".getBytes()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
