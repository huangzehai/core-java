package com.hzh.corejava.io.nio;

import java.nio.ByteBuffer;

public class ByteBufferExample {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        System.out.println("Position: " + byteBuffer.position());
        System.out.println("Limit: " + byteBuffer.limit());
        System.out.println("Capacity: " + byteBuffer.capacity());
        byteBuffer.put((byte) 'a');
        byteBuffer.put((byte) 'b');
        byteBuffer.put((byte) 'C');
        //java.nio.BufferOverflowException
        //byteBuffer.put("How are you doing?".getBytes());

        System.out.println("------------------After put------------------");
        System.out.println("Position: " + byteBuffer.position());
        System.out.println("Limit: " + byteBuffer.limit());
        System.out.println("Capacity: " + byteBuffer.capacity());
        byteBuffer.flip();
        System.out.println("------------------After flip------------------");
        System.out.println("Position: " + byteBuffer.position());
        System.out.println("Limit: " + byteBuffer.limit());
        System.out.println("Capacity: " + byteBuffer.capacity());
//        while (byteBuffer.hasRemaining()) {
//            System.out.println("get: " + (char) byteBuffer.get());
//            System.out.println("Position: " + byteBuffer.position());
//        }
    }
}
