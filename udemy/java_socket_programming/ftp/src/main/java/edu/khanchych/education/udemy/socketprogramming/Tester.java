package edu.khanchych.education.udemy.socketprogramming;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        FtpServer ftpServer = new FtpServer();
        new Thread(()-> {
            try {
                ftpServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        FtpClient ftpClient = new FtpClient("localhost", FtpServer.DEFAULT_SERVER_PORT);
        ftpServer.stop();
        byte[] fileContent = ftpClient.getFile("pom.xml");
        System.out.println(new String(fileContent));
    }
}
