package ui;

import util.UDPconnection;
import java.util.Scanner;

public class PeerH {
    public static void main(String[] args) {
        UDPconnection udpConnection = UDPconnection.getInstance();
        udpConnection.setPort(5001);
        udpConnection.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Write a message: ");
            String message = "[H]: " + scanner.nextLine();
            // udpConnection.sendMessage(message, "192.168.1.16", 8080);
            udpConnection.sendMessage(message, "127.0.0.1", 5000);
        }
    }
}