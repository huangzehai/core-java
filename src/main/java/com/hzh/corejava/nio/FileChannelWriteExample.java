package com.hzh.corejava.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by lenovo on 2017/3/13.
 */
public class FileChannelWriteExample {
    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("greeting.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        String text = "What is up?Guys";
        ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
        fileChannel.write(buffer);
        fileChannel.close();
    }
}
