package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPApp {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        try (DatagramSocket socket = new DatagramSocket(2082);) {

            byte[] buffer = new byte[256];
            DatagramPacket packetIn = new DatagramPacket(buffer, buffer.length);

            socket.receive(packetIn);

            InetAddress address = socket.getInetAddress();

            var msg = new String(packetIn.getData(), 0, packetIn.getLength());

            // response datagram
            DatagramPacket packetOut = new DatagramPacket(msg.getBytes(), msg.length(), address, packetIn.getPort());

            // Send response
            socket.send(packetOut);
            System.out.println(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
