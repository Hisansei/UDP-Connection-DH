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
            String destIp = "127.0.0.1";
            int destPort = 0;
            
            destPort = 5000;
            destIp = "192.168.1.16";

            System.out.print("Write a message: ");
            String message = "[H]: " + scanner.nextLine();
            udpConnection.sendMessage(message, destIp, destPort);
        }
    }
}
