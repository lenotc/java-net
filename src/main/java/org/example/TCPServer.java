package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {

    public static final int PORT = 1234;
    public static final String CLOSE = "[CLOSE]";

    public static void main(String[] args) {
        System.out.println("SERVER UP");
        create();
    }


    static void create() {
        try (var server = new ServerSocket(PORT);
             Socket link = server.accept()) {

            Scanner in      = new Scanner(link.getInputStream());
            PrintWriter out = new PrintWriter(link.getOutputStream(), true);

            int count = 0;

            String request = in.nextLine(); // RECEIVE FROM CLIENT
            while (!request.equals(CLOSE)) {
                System.out.println("MESSAGE RECEIVED.");
                count++;
                out.println("MESSAGE COUNT  " + count + " : " + request); // SEND TO CLIENT
                request = in.nextLine(); // RECEIVE FROM CLIENT
            }
            out.println(count + " messages received"); // SEND

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
