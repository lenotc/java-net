package org.example;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName("www.google.com");
        System.out.println(inetAddress);
        System.out.println("HOST ADDRESS: " + inetAddress.getHostAddress());
        System.out.println("CANONICAL HOST NAME: " + inetAddress.getCanonicalHostName());
        System.out.println("HOST NAME: " + inetAddress.getHostName());
        inetAddress.isReachable(1000);
    }
}