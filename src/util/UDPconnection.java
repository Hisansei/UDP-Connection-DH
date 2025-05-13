package util;

import java.io.IOException;
import java.net.*;

public class UDPconnection extends Thread {
    private static UDPconnection instance;
    private DatagramSocket socket;
    private boolean running = false;
    private int port;

    private UDPconnection() {
    }

    public static UDPconnection getInstance() {
        if (instance == null) {
            instance = new UDPconnection();
        }
        return instance;
    }

    public void setPort(int port) {
        this.port = port;
        try {
            this.socket = new DatagramSocket(this.port);
        } catch (SocketException e) {
            System.err.println("Failed to create socket on port " + port + ": " + e.getMessage());
        }
    }

    public void close() {
        running = false;
        if (socket != null) {
            socket.close();
        }
    }

    @Override
    public void run() {
        running = true;
        byte[] buffer = new byte[1024];

        while (running) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                System.out.println("Waiting for message on port " + port + "...");
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String received = new String(packet.getData(), 0, packet.getLength()).trim();

                System.out.println("Received from " + address + ":" + port + ": " + received);

            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        }
        if (socket != null) {
            socket.close();
        }
    }

    public void sendMessage(String msj, String ipDest, int portDest) {
        new Thread(() -> {
            try {
                InetAddress ipAddress = InetAddress.getByName(ipDest);
                DatagramPacket packet = new DatagramPacket(
                        msj.getBytes(), msj.length(), ipAddress, portDest);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}