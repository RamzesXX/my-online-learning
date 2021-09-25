package edu.khanchych.education.udemy.socketprogramming;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FtpServer {
    public static final int DEFAULT_SERVER_PORT = 1111;
    public static final String DEFAULT_BASE_PATH = "";

    private final int serverPort;
    private final String basePath;
    private boolean isServerRunning;

    public FtpServer(int serverPort, String basePath) {
        this.serverPort = serverPort;
        this.basePath = basePath;
        this.isServerRunning = false;
    }

    public FtpServer() {
        this(DEFAULT_SERVER_PORT, DEFAULT_BASE_PATH);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        isServerRunning = true;
        System.out.println("Server started");
        while (isServerRunning) {
            System.out.println("Waiting for client...");
            Socket sock = serverSocket.accept();
            new FtpServerThread(sock, basePath).start();
        }
        System.out.println("Server stopped");
        serverSocket.close();
    }

    public void stop() {
        isServerRunning = false;
    }
}

class FtpServerThread extends Thread {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAILURE = 400;
    private static final int BUFFER_SIZE = 1024;

    private final Socket socket;
    private final String basePath;

    public FtpServerThread(Socket socket, String basePath) {
        this.socket = socket;
        this.basePath = basePath;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.printf("%s: Connection accepted.%n", threadName);
        try {
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int byteRead = in.read(buffer);
            String fileName = new String(Arrays.copyOf(buffer, byteRead)).replaceAll("\\s*$", "");
            System.out.printf("%s: Client asked for ['%s']%n", threadName, fileName);
            byte[] fileContent = getFileContent(fileName);
            String response;
            if (fileContent == null) {
                response = String.format("%d%n", STATUS_FAILURE);
                out.write(response.getBytes());
                System.out.printf("%s: File ['%s'] does not exist%n", threadName, fileName);
            } else {
                response = String.format("%d%n", STATUS_SUCCESS);
                out.write(response.getBytes());
                out.write(fileContent);
                System.out.printf("%s: File ['%s'] sent%n", threadName, fileName);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("%s: Connection closed%n", threadName);
    }

    private byte[] getFileContent(String fileName) throws IOException {
        Path path = Paths.get(basePath, fileName);

        return Files.exists(path) ? Files.readAllBytes(path) : null;
    }
}