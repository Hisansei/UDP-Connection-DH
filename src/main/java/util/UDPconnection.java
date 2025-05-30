package util;

import java.io.IOException;
import java.net.*;

public class UDPconnection extends Thread {
    private static UDPconnection instance;
    private DatagramSocket socket;
    private boolean running = false;
    private int port;

    private UDPconnection() {}

    public static UDPconnection getInstance() {
        if (instance == null) {
            instance = new UDPconnection();
        }
        return instance;
    }

    public void setPort(int port) {
        this.port = port;
        try {
            if (this.socket == null || this.socket.isClosed()) {
                this.socket = new DatagramSocket(this.port);
                System.out.println("Socket created on port " + port);
            }
        } catch (SocketException e) {
            System.err.println("Failed to create socket on port " + port + ": " + e.getMessage());
        }
    }

    public void close() {
        running = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    /*
    * Modifique el código provisto de tal forma que: el hilo de recepción no  "muera" una
    * vez recibido el mensaje [10%]. Para cumplir este ítem modificamos el  método  run()
    * agregando un ciclo while que mantiene el hilo activo y escuchando mensajes de forma
    * continua hasta que se detenga explícitamente la conexión.
    */
    @Override
    public void run() {
        running = true;
        byte[] buffer = new byte[1024];

        while (running) {
            if (socket == null || socket.isClosed()) {
                System.out.println("Socket is not initialized or has been closed.");
                break;
            }

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
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    /**
     * Modifique el código provisto de tal forma que la lógica de transmisión
     * de paquetes quede en un hilo aparte [10%]. Para cumplir este  ítem, el
     * método sendMessage() crea un nuevo hilo para enviar el paquete  UDP de
     * forma asíncrona,  evitando  bloquear el  hilo principal  o la interfaz
     * durante el envío del mensaje.
     */
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

    public String receiveMessage() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength()).trim();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}