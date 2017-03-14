package com.hzh.corejava.aio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by huangzehai on 2017/3/2.
 */
public class BasicIoExample {
    public static void main(String[] args) throws IOException {
        Path home = Paths.get("/Users/huangzehai/data");
        Path profile = home.resolve(".test");
        Files.copy(profile, home.resolve(".test.bak"));
        Files.isSymbolicLink(profile);
    }
}
