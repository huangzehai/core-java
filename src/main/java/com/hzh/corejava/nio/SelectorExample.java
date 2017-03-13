package com.hzh.corejava.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huangzehai on 2017/3/13.
 */
public class SelectorExample extends Thread {
    private Logger logger = LoggerFactory.getLogger(SelectorExample.class);
    private ServerSocketChannel channel;
    private Selector selector;
    private volatile boolean running = false;

    public static void main(String args[]) throws IOException {
        SelectorExample server = new SelectorExample();
        try {
            server.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.stopServer();
    }

    public void startServer() {
        this.start();
    }

    public void stopServer() {
        running = false;
        if (this.channel != null) {
            try {
                this.channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Iterator<SelectionKey> keys = this.selector.keys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                SelectableChannel channel = key.channel();
                if (channel instanceof SocketChannel) {
                    SocketChannel socketChannel = (SocketChannel) channel;
                    Socket socket = socketChannel.socket();
                    String remoteHost = socket.getRemoteSocketAddress().toString();
                    logger.info("closing socket {}", remoteHost);
                    try {
                        socketChannel.close();
                    } catch (IOException e) {
                        logger.warn("Exception while closing socket", e);
                    }
                    key.cancel();
                }
            }
            logger.info("closing selector");
            selector.close();
        } catch (Exception ex) {
            logger.error("Exception while closing selector", ex);
        }
    }


    @Override
    public void run() {
        try {
            //建立Channel并绑定到9000端口
            channel = ServerSocketChannel.open().bind(new InetSocketAddress(9000));
            selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_ACCEPT);
            running = true;
            while (running) {
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isAcceptable()) {
                        // a connection was accepted by a ServerSocketChannel.
                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.
                    } else if (key.isReadable()) {
                        // a channel is ready for reading
                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                    }
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}