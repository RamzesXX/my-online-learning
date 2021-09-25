package edu.khanchych.education.udemy.socketprogramming;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        FtpServer ftpServer = new FtpServer();
        ftpServer.start();
        ftpServer.stop();
    }
}
