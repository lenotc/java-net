package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Files;

public class NIOIntro {
    public static void main(String[] args) {
        example_download();
    }

    static void example_download() {
        try {
            URL url = URI.create("https://ocsaly.com").toURL();
            URLConnection conn = url.openConnection();

            try (InputStream in = conn.getInputStream();
                 ReadableByteChannel channel = Channels.newChannel(in)) {
                ByteBuffer buffer = ByteBuffer.allocate(64);

                while (channel.read(buffer) > 0) {
                    System.out.println(new String(buffer.array()));
                    buffer.clear();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void example_byBuffer() {

        try {
            URL url = URI.create("https://google.com").toURL();
            URLConnection conn = url.openConnection();
            try (InputStreamReader in = new InputStreamReader(conn.getInputStream());
                 BufferedReader br = new BufferedReader(in)) {
                String myLine;

                while ((myLine = br.readLine()) != null) {
                    System.out.println(myLine);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    static void example1() {
        FileChannel fileChannel;
        DatagramChannel datagramChannel; // UDP
        SocketChannel socketChannel; // TCP
        ServerSocketChannel serverSocketChannel; // TCP
    }
}
