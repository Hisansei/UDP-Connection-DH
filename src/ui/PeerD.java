// PeerD.java
package ui;

import util.UDPconnection;
import java.util.Scanner;

public class PeerD {
    public static void main(String[] args) {
        UDPconnection udpConnection = UDPconnection.getInstance();
        udpConnection.setPort(5000);
        udpConnection.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Write a message: ");
            String message = "[D]: " + scanner.nextLine();
            udpConnection.sendMessage(message, "127.0.0.1", 8080);
        }
    }
}