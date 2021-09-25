package edu.khanchych.education.udemy.socketprogramming;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

import static edu.khanchych.education.udemy.socketprogramming.FtpServerThread.STATUS_SUCCESS;

public class FtpClient {
    private static final int BUFFER_SIZE = 1024;

    private final String serverAddress;
    private final int serverPort;

    public FtpClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public byte[] getFile(String fileName) throws IOException {
        Socket socket = new Socket(serverAddress, serverPort);
        System.out.printf("Connection to server [%s:%d] established%n", serverAddress, serverPort);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(fileName.getBytes());
        byte[] buffer = new byte[BUFFER_SIZE];
        int byteRead = in.read(buffer);

        byte[] fileContent = null;
        String response = new String(Arrays.copyOf(buffer, byteRead)).replaceAll("\\s*$", "");
        if (STATUS_SUCCESS == Integer.parseInt(response)) {
            ByteArrayOutputStream fileContentBuffer = new ByteArrayOutputStream();
            while ((byteRead = in.read(buffer)) != -1) {
                fileContentBuffer.write(buffer, 0, byteRead);
            }
            fileContentBuffer.flush();
            fileContent = fileContentBuffer.toByteArray();
        }
        socket.close();

        return fileContent;
    }
}
