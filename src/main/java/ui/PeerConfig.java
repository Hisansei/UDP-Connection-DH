package ui;

public final class PeerConfig {
    /*
    * Clase centralizada para configurar las direcciones  IP  y  puertos
    * de cada peer involucrado en la comunicación UDP. Aquí debe hacerse
    * la configuración para la comunicación: definir las IPs  y  puertos 
    * locales y remotos para cada nodo.
    */
    public static final String IP_PEER_D = "127.0.0.1";
    public static final int PORT_PEER_D = 5000;

    public static final String IP_PEER_H = "127.0.0.1";
    public static final int PORT_PEER_H = 5001;

    private PeerConfig() {}
}