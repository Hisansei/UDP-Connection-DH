package ui;

import ui.PeerConfig;
import java.util.Scanner;
import util.UDPconnection;

public class PeerH {

    private static String localIP = PeerConfig.IP_PEER_H;
    private static int localPort = PeerConfig.PORT_PEER_H;

    private static String remoteIP = PeerConfig.IP_PEER_D;
    private static int remotePort = PeerConfig.PORT_PEER_D;

    public static void main(String[] args) {
        UDPconnection udpConnection = UDPconnection.getInstance();
        udpConnection.setPort(localPort);
        udpConnection.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Write a message: ");
            String message = "[H]: " + scanner.nextLine();
            // udpConnection.sendMessage(message, "192.168.1.16", 8080);
            udpConnection.sendMessage(message, remoteIP, remotePort);
        }
    }
}