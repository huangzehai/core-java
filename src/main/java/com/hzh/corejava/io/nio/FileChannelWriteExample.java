package com.hzh.corejava.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by huangzehai on 2017/3/13.
 */
public class FileChannelWriteExample {
    public static void main(String[] args) throws IOException {
        write();
    }

    private static void write1() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("greeting.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        String text = "What is up?Guys";
        ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
        fileChannel.write(buffer);
        fileChannel.close();
    }

    private static void write() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("greeting.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(newData.length());
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while (buf.hasRemaining()) {
            fileChannel.write(buf);
        }
        fileChannel.close();
    }
}
