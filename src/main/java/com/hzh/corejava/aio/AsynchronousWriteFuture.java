package com.hzh.corejava.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.CREATE;


/**
 * Created by Adam on 2016/2/24.
 */
public class AsynchronousWriteFuture {
    public static ByteBuffer getDataBuffer() {
        String lineSeparator = System.getProperty("line.separator");

        StringBuilder sb = new StringBuilder();
        sb.append("test");
        sb.append(lineSeparator);

        String str = sb.toString();
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.wrap(str.getBytes(cs));

        return bb;
    }

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("test.txt");

        try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(path,
                WRITE, CREATE)) {
            ByteBuffer dataBuffer = getDataBuffer();
            Future<Integer> result = afc.write(dataBuffer, 0);
            while (!result.isDone()) {
                System.out.println("Sleeping for 1 milliseconds...");
                Thread.sleep(1);
            }
            int writtenBytes = result.get();
            System.out.format("%s bytes written  to  %s%n", writtenBytes,
                    path.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
