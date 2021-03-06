package com.khanchych.udemy.javaindepth.playground;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class IO {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // loadResponsesByUrlFromStream(new BufferedReader(new InputStreamReader(System.in, "UTF-8")));
//        fileDemo();
//        File file = new File("/home/../etc");
        byte[] array;
        try (
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ObjectOutput oos = new ObjectOutputStream(os)) {
            oos.writeObject("Hello world!");
            array = os.toByteArray();
        }
        try (
                ByteArrayInputStream is = new ByteArrayInputStream(array);
                ObjectInput ois = new ObjectInputStream(is)) {
            System.out.println((String)ois.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(file.getName());
    }

    private static void fileDemo() {
        File file = new File("/home/../etc");
        System.out.println(file);
    }

    private static void loadResponsesByUrlFromStream(BufferedReader urlReader) throws IOException, URISyntaxException {
        String urlString, line;
        int count = 0;
        while (Objects.nonNull(urlString = urlReader.readLine())) {
            URL url = new URI(urlString.trim()).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(connection.getResponseCode());
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(++count + ".txt"), "UTF-8"))) {
                while (Objects.nonNull(line = reader.readLine())) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
