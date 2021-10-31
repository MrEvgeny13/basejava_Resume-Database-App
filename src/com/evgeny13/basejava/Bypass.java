package com.evgeny13.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Bypass {

    public static void main(String[] args) {
        File startFolder = new File(".\\src\\com\\evgeny13\\basejava");

        try {
            getAllFiles(" ", startFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getAllFiles(String indent, File startFile) throws IOException {
        File[] files = startFile.listFiles();

        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile()) {
                System.out.println(indent + "file: " + file.getName());
            } else {
                System.out.println(indent + "dir: " + file.getName());
                getAllFiles(indent + " ", file);
            }
        }
    }
}
