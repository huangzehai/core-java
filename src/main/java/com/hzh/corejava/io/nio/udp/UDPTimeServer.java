package com.hzh.corejava.io.nio.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;

/**
 * Created by huangzehai on 2017/3/14.
 */
public class UDPTimeServer {
    public final static int DEFAULT_PORT = 37;

    public static void main(String[] args) throws IOException {
        int port = DEFAULT_PORT;
        ByteBuffer in = ByteBuffer.allocate(8192);
        ByteBuffer out = ByteBuffer.allocate(8);
        out.order(ByteOrder.BIG_ENDIAN);
        SocketAddress address = new InetSocketAddress(port);
        DatagramChannel channel = DatagramChannel.open();
        DatagramSocket socket = channel.socket();
        socket.bind(address);
        System.err.println("bound to " + address);
        while (true) {
            try {
                in.clear();
                SocketAddress client = channel.receive(in);
                //打印客户端信息
                System.err.println(client);
                in.flip();
                while (in.hasRemaining()) {
                    System.out.println(in.get());
                }
                //返回时间
                long secondsSince1970 = System.currentTimeMillis();
                out.clear();
                out.putLong(secondsSince1970);
                out.flip();
                out.position(4);
                channel.send(out, client);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }
}
