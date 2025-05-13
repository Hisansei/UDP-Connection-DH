package ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import util.UDPconnection;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.application.Application;

public class Chat extends Application {

    private UDPconnection udpConnection;

    @Override
    public void start(Stage primaryStage) {
        // Área para mostrar los mensajes ..........................
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setPrefSize(400, 300);

        // Campo para escribir los mensajes ........................
        TextField inputField = new TextField();
        inputField.setPromptText("Type your message...");
        Button sendButton = new Button("Send Message");

        // Enviar mensaje cuando se presiona el botón...............
        sendButton.setOnAction(e -> { 
            String message = inputField.getText();
            if (!message.isEmpty()) {
                udpConnection.sendMessage(message, "127.0.0.1", 5001); // Ajustar según caso!
                chatArea.appendText("You: " + message + "\n");    // Mostrar el mensaje enviado en la interfaz
                inputField.clear();                                              // Limpiar el campo de texto
            }
        });

        // Organizar los elementos en la ventana....................
        StackPane root = new StackPane();
        root.getChildren().addAll(chatArea, inputField, sendButton);
        
        inputField.setTranslateY(140); 
        sendButton.setTranslateY(180);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("UDP Connection Interface");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Configurar la conexión UDP:
        udpConnection = UDPconnection.getInstance();
        udpConnection.setPort(5000);  // Puerto local para escuchar
        udpConnection.start();

        // Recibir mensajes en un hilo separado y actualizar el chat:
        new Thread(() -> {
            while (true) {
                String receivedMessage = udpConnection.receiveMessage();
                if (receivedMessage != null) {
                    // Mostrar mensaje recibido en la interfaz
                    chatArea.appendText("Peer: " + receivedMessage + "\n");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}