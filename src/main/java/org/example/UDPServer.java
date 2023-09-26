package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    private static final int PORT = 2082;

    public static void main(String[] args) {
        execute();
    }

    static void execute() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            handleClient(socket);

        } catch (SocketException e) {
            System.out.println("UNABLE TO OPEN PORT: " + PORT);
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    static void handleClient(DatagramSocket socket) throws IOException {
        String messageIn, messageOut;
        int count = 0;
        DatagramPacket packetIn, packetOut;

        do {
            byte[] buffer = new byte[256];
            packetIn = new DatagramPacket(buffer, buffer.length);

            socket.receive(packetIn);

            messageIn = new String(packetIn.getData(), 0, packetIn.getLength());
            System.out.println("MESSAGE RECEIVED " + messageIn);
            count++;
            messageOut = "MESSAGE OUT " + count + " : " + messageIn;

            packetOut = new DatagramPacket(messageOut.getBytes(), messageOut.length(), packetIn.getAddress(), packetIn.getPort());
            socket.send(packetOut);
        } while (!messageIn.equals("CLOSE"));
    }
}
