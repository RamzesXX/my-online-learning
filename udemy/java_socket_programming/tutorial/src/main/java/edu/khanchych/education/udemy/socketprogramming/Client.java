package edu.khanchych.education.udemy.socketprogramming;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import static edu.khanchych.education.udemy.socketprogramming.Configuration.SERVER_IP_ADDRESS;
import static edu.khanchych.education.udemy.socketprogramming.Configuration.SERVER_PORT;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        out.write("hello server".getBytes());
        byte[] response = new byte[1024];
        int byteRead = in.read(response);
        String responseString = new String(Arrays.copyOf(response, byteRead));
        System.out.println("Server answered:" + responseString);
        client.close();
    }
}
