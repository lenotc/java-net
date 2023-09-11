package org.example;

import java.net.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkPrograming {
    public static void main(String[] args) {
        example_5();
    }

    static void example_5() {
        try {
            InetAddress net = InetAddress.getByName("google.com");
            System.out.println(Arrays.toString(net.getAddress()));
            if (net.isAnyLocalAddress()) {
                System.out.println("YES");
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }

    static void example_4() {
        try {
            InetAddress[] names = InetAddress.getAllByName("google.com");
            for (InetAddress name : names) {
                System.out.println(name);
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
    }

    static void example_3() {
        String site = "https://en.wikipedia.org/wiki/Cryptography";
        try {
            URL url = URI.create(site).toURL();
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            System.out.println(url.getRef());
            System.out.println(url.getPort());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static void example_2() {
        String site = "https://en.wikipedia.org/wiki/Cryptography/";
        URI uri = URI.create(site);

        System.out.println(uri.getAuthority());
        System.out.println(uri.getPath());
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getScheme());
        System.out.println(uri.getQuery());
    }

    static void example_1() {
        try {
            Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
            System.out.println("Network Display: \n");
            for (NetworkInterface el : Collections.list(net)) {
                System.out.printf("%-8s %-32s \n", el.getName(), el.getDisplayName());
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
