package com.hzh.corejava.aio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * Created by huangzehai on 2017/3/14.
 */
public class AsynchronousServer {
    public static void main(String[] args) throws Exception {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel
                .open();
        InetSocketAddress sAddr = new InetSocketAddress(InetAddress.getLocalHost(), 5000);
        server.bind(sAddr);
        System.out.format("Server is listening at %s%n", sAddr);
        ServerAttachment attach = new ServerAttachment();
        attach.server = server;
        server.accept(attach, new ConnectionHandler());
        Thread.currentThread().join();
    }
}

class ServerAttachment {
    AsynchronousServerSocketChannel server;
    AsynchronousSocketChannel client;
    ByteBuffer buffer;
    SocketAddress clientAddr;
    boolean isRead;
}

class ConnectionHandler implements
        CompletionHandler<AsynchronousSocketChannel, ServerAttachment> {
    @Override
    public void completed(AsynchronousSocketChannel client, ServerAttachment attach) {
        try {
            SocketAddress clientAddr = client.getRemoteAddress();
            System.out.format("Accepted a  connection from  %s%n", clientAddr);
            attach.server.accept(attach, this);
            ServerReadWriteHandler rwHandler = new ServerReadWriteHandler();
            ServerAttachment newAttach = new ServerAttachment();
            newAttach.server = attach.server;
            newAttach.client = client;
            newAttach.buffer = ByteBuffer.allocate(2048);
            newAttach.isRead = true;
            newAttach.clientAddr = clientAddr;
            client.read(newAttach.buffer, newAttach, rwHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable e, ServerAttachment attach) {
        System.out.println("Failed to accept a  connection.");
        e.printStackTrace();
    }
}

class ServerReadWriteHandler implements CompletionHandler<Integer, ServerAttachment> {
    @Override
    public void completed(Integer result, ServerAttachment attach) {
        if (result == -1) {
            try {
                attach.client.close();
                System.out.format("Stopped   listening to the   client %s%n",
                        attach.clientAddr);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if (attach.isRead) {
            attach.buffer.flip();
            int limits = attach.buffer.limit();
            byte bytes[] = new byte[limits];
            attach.buffer.get(bytes, 0, limits);
            Charset cs = Charset.forName("UTF-8");
            String msg = new String(bytes, cs);
            System.out.format("Client at  %s  says: %s%n", attach.clientAddr,
                    msg);
            attach.isRead = false; // It is a write
            attach.buffer.rewind();

        } else {
            // Write to the client
            attach.client.write(attach.buffer, attach, this);
            attach.isRead = true;
            attach.buffer.clear();
            attach.client.read(attach.buffer, attach, this);
        }
    }

    @Override
    public void failed(Throwable e, ServerAttachment attach) {
        e.printStackTrace();
    }
}
