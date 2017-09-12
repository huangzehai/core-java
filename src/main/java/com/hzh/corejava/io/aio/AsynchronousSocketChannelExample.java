package com.hzh.corejava.io.aio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by huangzehai on 2017/3/14.
 */
public class AsynchronousSocketChannelExample {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> connectFuture = socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 5000));
        connectFuture.get();
        String requestBody = "How are you? Server" + System.getProperty("line.separator");
        TheAttachment attachment = new TheAttachment();
        ByteBuffer buffer = ByteBuffer.wrap(requestBody.getBytes());
        attachment.channel = socketChannel;
        attachment.buffer = buffer;
        attachment.isRead = false;
        socketChannel.write(buffer, attachment, new Handler());
        socketChannel.close();
    }
}

class TheAttachment {
    AsynchronousSocketChannel channel;
    ByteBuffer buffer;
    boolean isRead;
}

class Handler implements CompletionHandler<Integer, TheAttachment> {

    @Override
    public void completed(Integer result, TheAttachment attachment) {
        System.out.println("READ or write");
    }

    @Override
    public void failed(Throwable exc, TheAttachment attachment) {

    }
}
