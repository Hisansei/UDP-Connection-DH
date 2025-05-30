# <p align="center">Taller UDP Connection - Universidad Icesi</p>

### <p align="center">Facultad de Ingeniería, Diseño y Ciencias Aplicadas</p>

---

### **Profesor:** Ing. Nicolás Javier Salazar Echeverry

---

**Fecha de asignación:** 8 de mayo de 2025

**Fecha de entrega:** &nbsp;&nbsp; 30 de mayo de 2025

---

## **Equipo de Trabajo**

- Daniel Esteban Arcos Cerón &nbsp;&nbsp;&nbsp;&nbsp; [A00400760]

- Hideki Tamura Hernández &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[A00348618]

---

## **Descripción General del Proyecto**

<p align="justify">El objetivo de este proyecto fue desarrollar una comunicación entre peers utilizando <strong>UDP</strong> en Java, como parte del taller de la asignatura <strong>Computación en Internet I</strong> de la Universidad Icesi. Se implementaron varios Peers que, utilizando la clase <code>UDPconnection</code>, pueden enviar y recibir mensajes de texto entre sí. Además, se creó una interfaz gráfica con <strong>JavaFX</strong> para facilitar la interacción del usuario con el programa.</p>

<p align="justify">El proyecto se estructura de manera modular, donde la clase <code>UDPconnection</code> gestiona la lógica de comunicación de red, y la clase <code>Chat</code> es responsable de la interfaz gráfica.</p>

---

## **Estructura del Proyecto**

```plaintext
UDP-Connection-DH/
│
├── bin/                       # Directorio que contiene los archivos compilados ...........
├── src/                       # Contiene el código fuente del proyecto ....................
│   ├── ui/                    # Contiene las clases relacionadas con la interfaz de usuario
│   │   ├── Chat.java          # Interfaz gráfica principal (JavaFX) .......................
│   │   ├── PeerD.java         # Implementación para Peer D ................................
│   │   ├── PeerH.java         # Implementación para Peer H ................................
│   │   ├── PeerConfig.java    # Implementación para configurar puertos e IPs ..............
│   │
│   └── util/                    # Contiene la lógica de comunicación ......................
│       └── UDPconnection.java   # Clase para manejar la conexión UDP ......................
│
├── target/                # Directorio generado por Maven .................................
├── pom.xml                # Archivo de configuración de Maven .............................
└── README.md              # Este archivo!
````

---

## **Instrucciones Básicas de Ejecución**

### Archivo de configuración

<p align="justify">En primer lugar, toda la configuración de puertos e IPs se encuentra centralizada en la clase <code>PeerConfig</code>. Esta clase define las direcciones IP y los puertos locales y remotos para cada peer, y debe ser actualizada para cambiar la configuración de comunicación.</p>

```java
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
```

---

### Ejecución desde línea de comandos

> **Nota:** Por ahora, la interfaz gráfica sólo es ejecutable mediante línea de comandos usando Maven.

1. Para iniciar la interfaz gráfica principal (que por defecto se ejecuta como Peer D en puerto 5000):

```bash
mvn clean javafx:run
```

<p align="justify">Si la configuración en <code>PeerConfig.java</code> fue correcta, al iniciarse la interfaz podrás enviar y recibir mensajes con el peer configurado hacia el otro peer (por defecto hacia Peer H en puerto 5001).</p>

<p align="justify"><strong>Importante:</strong> Si planeas ejecutar este programa en dos computadores diferentes, debes modificar la configuración en <code>PeerConfig.java</code> para que refleje las direcciones IP reales de cada máquina en la red. Específicamente:</p> 

<ul>
   <li><p align="justify">Para cada peer, actualiza la constante <code>IP_PEER_X</code> con la dirección IP local o pública asignada a la máquina donde se ejecuta ese peer.</p></li>
   <li><p align="justify">Asegúrate de que los puertos configurados (<code>PORT_PEER_D</code>, <code>PORT_PEER_H</code>, etc.) estén libres y accesibles en cada computador, y que no existan firewalls bloqueando el tráfico UDP en esos puertos.</p></li>
   <li><p align="justify">La IP y puerto que un peer utiliza para enviar mensajes deben coincidir con la IP y puerto donde el peer receptor está escuchando, asegurando la comunicación correcta entre ambos.</p></li>
   <li><p align="justify">Si ambos computadores están en la misma red local, puedes usar las IPs privadas asignadas por el router.</p></li>
   <li><p align="justify">Esta configuración garantiza que los peers se encuentren en la red y puedan enviar y recibir mensajes correctamente usando UDP.</p></li>
</ul>

---

### Para pruebas en solitario (multi-consola)

<p align="justify">Para probar la comunicación local entre peers en tu misma máquina, abre dos consolas. En una de ellas, ejecuta el programa tal como se indica en la sección anterior, y en la otra consola, ejecuta el peer opuesto utilizando los comandos siguientes:</p>

* **Si tu eres `PeerD`, ejecutar `PeerH`:**

```bash
mvn exec:java -Dexec.mainClass="ui.PeerH"
```

* **Si tu eres `PeerH`, ejecutar `PeerD`:**

```bash
mvn exec:java -Dexec.mainClass="ui.PeerD"
```

---

### Recordatorio:

* PeerD usa puerto local **5000** y envía a PeerH en puerto **5001**.
* PeerH usa puerto local **5001** y envía a PeerD en puerto **5000**.
* Ambas instancias deben ejecutarse simultáneamente para que la comunicación funcione correctamente.
* Para pruebas locales, las IPs están configuradas en `127.0.0.1`. Si se usan máquinas diferentes, cambiar a las IPs de red correspondientes.
