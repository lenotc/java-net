package org.example;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    private static final int PORT = 2082;
    public static void main(String[] args) {
        execute();
    }

    static void execute() {
        try (DatagramSocket socket = new DatagramSocket()) {
            accessServer(socket);
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("PORT IS NOT ENABLE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void accessServer(DatagramSocket socket) throws IOException {
        String request = "", response = "";
        Scanner cmd = new Scanner(System.in);
        InetAddress host = InetAddress.getLocalHost();
        DatagramPacket packetOut, packetIn;
        byte[] buffer;

        do {
            System.out.print("Enter message:  ");
            request = cmd.nextLine();

            packetOut = new DatagramPacket(request.getBytes(), request.length(), host, PORT);
            socket.send(packetOut);

            buffer = new byte[256];
            packetIn = new DatagramPacket(buffer, buffer.length);
            socket.receive(packetIn);

            response = new String(packetIn.getData(), 0, packetIn.getLength());
            System.out.println("[CLIENT]" + response);
        } while (!request.equals("CLOSE"));
    }
}
