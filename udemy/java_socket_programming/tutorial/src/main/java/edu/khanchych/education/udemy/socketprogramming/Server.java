package edu.khanchych.education.udemy.socketprogramming;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import static edu.khanchych.education.udemy.socketprogramming.Configuration.SERVER_PORT;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(SERVER_PORT);
        int numberClients = 0;
        while (true) {
            numberClients++;
            System.out.println("Waiting for client...");
            new ServerThread(server.accept()).start();

            if (numberClients > 2) {
                break;
            }
        }
        server.close();
    }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client is connected.");

        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            byte[] request = new byte[1024];
            int byteRead = in.read(request);
            String requestString = new String(Arrays.copyOf(request, byteRead));
            System.out.println("Client says:" + requestString);
            out.write("hello from server".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}