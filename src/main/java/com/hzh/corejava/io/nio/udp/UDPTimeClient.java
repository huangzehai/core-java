package com.hzh.corejava.io.nio.udp;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;

/**
 * Created by huangzehai on 2017/3/14.
 */
public class UDPTimeClient {
    public static void main(String[] args) throws Exception {

        DatagramChannel channel = DatagramChannel.open();
        // port 0 selects any available port
        SocketAddress address = new InetSocketAddress(0);
        DatagramSocket socket = channel.socket();
        socket.setSoTimeout(5000);
        socket.bind(address);

        SocketAddress server = new InetSocketAddress(InetAddress.getLocalHost(), 37);
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        // time protocol always uses big-endian order
        buffer.order(ByteOrder.BIG_ENDIAN);
        // Must put at least one byte of data in the buffer;
        // it doesn't matter what it is.
        buffer.put((byte) 65);
        buffer.flip();

        channel.send(buffer, server);

        buffer.clear();
        buffer.put((byte) 0).put((byte) 0).put((byte) 0).put((byte) 0);
        channel.receive(buffer);
        buffer.flip();
        long secondsSince1970 = buffer.getLong();

        System.out.println(secondsSince1970);
        channel.close();
    }
}
