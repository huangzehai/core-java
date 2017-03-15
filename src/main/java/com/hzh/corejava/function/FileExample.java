package com.hzh.corejava.function;

import java.io.File;

/**
 * Created by lenovo on 2017/3/15.
 */
public class FileExample {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        for (File file : hiddenFiles) {
            System.out.println(file.getName());
        }
    }
}
