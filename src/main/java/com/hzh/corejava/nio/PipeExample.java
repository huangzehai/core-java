package com.hzh.corejava.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by huangzehai on 2017/3/15.
 */
public class PipeExample {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();

        while (buf.hasRemaining()) {
            sinkChannel.write(buf);
        }

        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer inBuffer = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(inBuffer);
        while (bytesRead != -1) {
            inBuffer.flip();
            while (inBuffer.hasRemaining()) {
                System.out.print((char) inBuffer.get());
            }
            inBuffer.clear();
            bytesRead = sourceChannel.read(inBuffer);
        }
    }
}
