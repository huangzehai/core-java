package com.hzh.corejava.aio;

import java.nio.ByteBuffer;

/**
 * Created by lenovo on 2017/3/14.
 */
public class BufferExample {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        String text = "how are you today?";
        //throw BufferOverflowException
        buffer.put(ByteBuffer.wrap(text.getBytes()));
    }
}
