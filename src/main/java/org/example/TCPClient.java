package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

    public static final String CLOSE = "[CLOSE]";
    public static final int PORT = 1234;

    public static void main(String[] args) {
        create();
    }

    static void create() {
        try {
            InetAddress host = InetAddress.getLocalHost();

            try (Socket link = new Socket(host, PORT)) {
                Scanner net     = new Scanner(link.getInputStream());
                Scanner cmd     = new Scanner(System.in);
                PrintWriter out = new PrintWriter(link.getOutputStream(), true);

                handleRequest(cmd, out, net);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void handleRequest(Scanner cmd, PrintWriter out, Scanner net) {
        String request, response;
        do {
            System.out.print("Send Message: ");
            request = cmd.nextLine(); // TYPE IN CMD

            out.println(request); // SEND TO SERVER
            response = net.nextLine(); // RECEIVE RESPONSE FROM SERVER
            System.out.println("RESPONSE >> " + response);
        } while (!request.equals(CLOSE));
    }
}
