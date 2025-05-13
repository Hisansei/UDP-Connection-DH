# <p align="center">Taller UDP Connection - Universidad Icesi</p>

### <p align="center">Facultad de Ingeniería, Diseño y Ciencias Aplicadas</p>

---

### Profesor: Ing. Nicolás Javier Salazar Echeverry

**Fecha de asignación:** 8 de mayo de 2025

---

## **Equipo de Trabajo**

- Daniel Esteban Arcos Cerón &nbsp;&nbsp;&nbsp;&nbsp; [A00400760]

- Hideki Tamura Hernández &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[A00348618]

---

## **Descripción General del Proyecto**

El objetivo de este proyecto fue desarrollar una comunicación entre peers utilizando **UDP** en Java, como parte del taller de la asignatura **Computación en Internet I** de la Universidad Icesi. Se implementaron varios Peers que, utilizando la clase `UDPconnection`, pueden enviar y recibir mensajes de texto entre sí. Además, se creó una interfaz gráfica con **JavaFX** para facilitar la interacción del usuario con el programa.

El proyecto se estructura de manera modular, donde la clase `UDPconnection` gestiona la lógica de comunicación de red, y la clase `Chat` es responsable de la interfaz gráfica.

---

## **Estructura del Proyecto**

```plaintext
UDP-Connection-DH/
│
├── bin/                   # Directorio que contiene los archivos compilados
├── src/                   # Contiene el código fuente del proyecto
│   ├── ui/                # Contiene las clases relacionadas con la interfaz de usuario
│   │   ├── Chat.java      # Interfaz gráfica principal (JavaFX)
│   │   ├── PeerD.java     # Implementación para Peer D
│   │   ├── PeerH.java     # Implementación para Peer H
│   │
│   └── util/              # Contiene la lógica de comunicación
│       └── UDPconnection.java   # Clase para manejar la conexión UDP
│
├── target/                # Directorio generado por Maven, contiene archivos compilados
├── pom.xml                # Archivo de configuración de Maven
└── README.md              # Este archivo
```

---

## **Instrucciones Básicas de Ejecución**

### **Configuración de Puertos**

* **PeerD** y **PeerH** usan puertos **5000** y **5001**, respectivamente.
* Ambos peers deben estar ejecutándose simultáneamente, por lo que se deben usar puertos diferentes para cada uno.

### **Ejecución de la Main Class**

1. **Para ejecutar la clase principal (Chat)**, usa el siguiente comando:

   ```bash
   mvn exec:java -Dexec.mainClass="ui.Chat"
   ```

2. **Para ejecutar un Peer**, utiliza el comando correspondiente para cada uno:

   * **Para ejecutar `PeerD`**:

     ```bash
     mvn exec:java -Dexec.mainClass="ui.PeerD"
     ```
   * **Para ejecutar `PeerH`**:

     ```bash
     mvn exec:java -Dexec.mainClass="ui.PeerH"
     ```

### **Ejecución en solitario (Pruebas Locales)**

Si estás probando la implementación en tu máquina local, puedes utilizar las **IPs locales** `127.0.0.1` para la comunicación entre **PeerD** y **PeerH**.

* **PeerD** envía mensajes a `127.0.0.1:5001`.
* **PeerH** envía mensajes a `127.0.0.1:5000`.

Recuerda que estas IPs son para pruebas locales. Si los peers están en máquinas diferentes, necesitarás cambiar las IPs a las direcciones correspondientes de cada máquina.

---
