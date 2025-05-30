package ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import util.UDPconnection;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.application.Application;

public class Chat extends Application {

    private UDPconnection udpConnection;
    private volatile boolean running = true;

    @Override
    public void start(Stage primaryStage) {
        // Área para mostrar los mensajes ..........................
        TextArea chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setPrefSize(400, 300);
        chatArea.setStyle("-fx-background-color: #f0f0f0; -fx-font-size: 14px;");

        // Campo para escribir los mensajes ........................
        TextField inputField = new TextField();
        inputField.setPromptText("Type your message...");
        inputField.setStyle("-fx-padding: 10px; -fx-font-size: 14px;");

        Button sendButton = new Button("Send Message");
        sendButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        // Enviar mensaje cuando se presiona el botón...............
        sendButton.setOnAction(e -> { 
            String message = inputField.getText();
            if (!message.isEmpty()) {
                udpConnection.sendMessage(message, "127.0.0.1", 5001);  // Ajustar según caso! Este es el destinatario!
                chatArea.appendText("You: " + message + "\n");    // Mostrar el mensaje enviado en la interfaz
                inputField.clear();                                               // Limpiar el campo de texto
            }
        });

        // Botón para salir.........................................
        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        
        exitButton.setOnAction(e -> {
            shutdown();
            Platform.exit();
        });

        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER_LEFT);
        buttonsBox.getChildren().addAll(sendButton, exitButton);

        // Organizar los elementos en la ventana....................
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(chatArea, inputField, buttonsBox);

        Scene scene = new Scene(layout, 500, 400);
        scene.setFill(Color.web("#f5f5f5"));
        primaryStage.setTitle("UDP Connection Interface");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(e -> {
            shutdown();
        });

        primaryStage.show();

        // Configurar la conexión UDP:
        udpConnection = UDPconnection.getInstance();
        udpConnection.setPort(5000);  // Puerto local para escuchar; Por default es el de Peer D!
        udpConnection.start();

        // Recibir mensajes en un hilo separado y actualizar el chat:
        Thread receiverThread = new Thread(() -> {
            while (running) {
                String receivedMessage = udpConnection.receiveMessage();
                if (receivedMessage != null) {
                    Platform.runLater(() -> chatArea.appendText("Peer " + receivedMessage + "\n"));
                }
            }
        });
        receiverThread.setDaemon(true);
        receiverThread.start();
    }

    private void shutdown() {
        running = false;
        if (udpConnection != null) {
            udpConnection.close();
        }
    }

    @Override
    public void stop() throws Exception {
        shutdown();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}