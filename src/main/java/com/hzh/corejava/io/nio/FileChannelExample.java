package com.hzh.corejava.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by huangzehai on 2017/3/11.
 */
public class FileChannelExample {
    public static void main(String[] args) throws IOException {
//        RandomAccessFile aFile = new RandomAccessFile("README.md", "rw");
//        FileChannel inChannel = aFile.getChannel();
        try (FileChannel fileChannel = FileChannel.open(Paths.get("README.md"), StandardOpenOption.READ)) {
            ByteBuffer buf = ByteBuffer.allocate(4);
            int bytesRead = fileChannel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
        }
//        aFile.close();
    }

}
