package com.khanchych.udemy.javaindepth.exceptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourcesDemo {
    final static String inFileStr = "./src/main/resources/Book.txt";
    final static String outFileStr = "./src/main/resources/Book_out.txt";

    public static void copyFileWithArm() throws IOException {

        System.out.println("\n Inside copyFileWithArm ...");

        try (
                Test test = new Test();
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))
            ){
            byte[] byteBuf = new byte[4000];
            int numByteRead;

            while ((numByteRead = in.read(byteBuf)) != -1) {
                out.write(byteBuf, 0, numByteRead);
            }

            throw new IOException("Important Exception!!");
        }
    }

    public static void main(String[] args) {
        try {
            copyFileWithArm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Test implements AutoCloseable {

    @Override
    public void close() throws IOException {
        throw new IOException("Trivial Exception");
    }
}