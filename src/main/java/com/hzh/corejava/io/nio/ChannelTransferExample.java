package com.hzh.corejava.io.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by huangzehai on 2017/3/11.
 */
public class ChannelTransferExample {
    public static void main(String[] args) throws IOException {
        FileChannel fromChannel = FileChannel.open(Paths.get("README.md"));
        FileChannel toChannel = FileChannel.open(Paths.get("toFile.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
        fromChannel.close();
        toChannel.close();
    }
}
