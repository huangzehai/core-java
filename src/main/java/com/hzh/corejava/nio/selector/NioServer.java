package com.hzh.corejava.nio.selector;

import com.hzh.corejava.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Crunchify.com
 */

public class NioServer {

    private Logger logger = LoggerFactory.getLogger(NioServer.class);

    public static void main(String[] args) throws IOException {

        // Selector: multiplexor of SelectableChannel objects
        Selector selector = Selector.open(); // selector is open here

        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("localhost", Constants.PORT);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        serverSocket.bind(address);

        // Adjusts this channel's blocking mode.
        serverSocket.configureBlocking(false);

        int ops = serverSocket.validOps();
        SelectionKey selectionKey = serverSocket.register(selector, ops, null);

        // Infinite loop..
        // Keep server running
        while (true) {

            log("i'm a server and i'm waiting for new connection and buffer select...");
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey myKey = iterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {
                    SocketChannel client = serverSocket.accept();

                    // Adjusts this channel's blocking mode to false
                    client.configureBlocking(false);

                    // Operation-set bit for read operations
                    client.register(selector, SelectionKey.OP_READ );
                    log("Connection Accepted: " + client.getLocalAddress() + "\n");

                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {

                    SocketChannel client = (SocketChannel) myKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    client.read(byteBuffer);
                    String result = new String(byteBuffer.array()).trim();

                    log("Message received: " + result);

                    if (result.equals("Crunchify")) {
                        client.close();
                        log("\nIt's time to close connection as we got last company name 'Crunchify'");
                        log("\nServer will keep running. Try running client again to establish new connection");
                    }
                }
                iterator.remove();
            }
        }
    }

    private static void log(String str) {
        System.out.println(str);
    }
}
