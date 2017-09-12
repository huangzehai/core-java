package com.hzh.corejava.io.nio;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/15.
 */
public class EchoServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 5000));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);

        while (true) {
            selector.select();
            Set selectedKeys = selector.selectedKeys();
            Iterator iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = (SelectionKey) iter.next();

                if (key.isAcceptable()) {
                    SocketChannel client = serverSocket.accept();
                    System.out.println("accept client " + client);
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    System.out.println("Read for reading");
                    SocketChannel client = (SocketChannel) key.channel();
                    System.out.println("client " + client);
                    client.read(buffer);
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                    client.close();
                }
                iter.remove();
            }
        }
    }

    public static Process start() throws IOException, InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = EchoServer.class.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

        return builder.start();
    }
}
