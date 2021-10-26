package com.evgeny13.basejava;

import java.io.File;
import java.io.IOException;

public class Bypass {

    public static void main(String[] args) {
        String startFolder = "./src/com/evgeny13/basejava";

        try {
            getAllFiles(startFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAllFiles(String path) throws IOException {
        File startFolder = new File(path);
        File[] files = startFolder.listFiles();

        if (files == null) {
            throw new IOException();
        }

        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            } else {
                getAllFiles(file.getPath());
            }
        }
    }
}
