package com.hzh.corejava.phantomjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Screenshot {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("ids.txt"));
        String commandFormat = "D:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe D:\\screenshot.js %s %s";
        lines.parallelStream().forEach(id -> {
            try {
                id = id.trim();
                String url = "https://itunes.apple.com/cn/app/" + id;
                String output = "D:\\iTunes\\" + id + ".png";
                String command = String.format(commandFormat, url, output);
                System.out.println(command);
                Process p = Runtime.getRuntime().exec(command);
                p.waitFor();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("=================done");

    }
}
