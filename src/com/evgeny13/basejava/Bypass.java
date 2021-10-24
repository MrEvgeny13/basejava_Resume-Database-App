package com.evgeny13.basejava;

import java.io.File;
import java.io.IOException;

public class Bypass {

    public static void getAllFiles(String path) throws IOException {
        File startFolder = new File(path);
        File[] files = startFolder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                printFileName(file);
            } else {
                getAllFiles(file.getPath());
            }
        }
    }

    private static void printFileName(File file) throws IOException {
        System.out.println(file.getCanonicalPath());
    }

    public static void main(String[] args) {
        String startFolder = "C:\\Users\\Evgeny\\IdeaProjects\\JavaOPs\\basejava";

        try {
            getAllFiles(startFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
