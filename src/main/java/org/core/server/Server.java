package org.core.server;

import org.core.client.Client;
import org.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server class for the for {@code Client} class. This class is used to create a server that listens for connections from clients.
 * The server will create a new thread for each client connection.
 *
 * @see Client
 *
 * @author Logan Abernathy
 */

public class Server implements Runnable {
    private final List<PrintWriter> clientWriters = new ArrayList<>();
    private final int maxClients; // Maximum number of clients allowed
    private int connectedClients = 0; // Current number of connected clients

    public Server(int maxClients) {
        this.maxClients = maxClients;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is waiting for connections...");

            while (true) {
                if (connectedClients < maxClients) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket);

                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    clientWriters.add(out);
                    connectedClients++;

                    // Create a new thread to handle the client connection
                    new Thread(new ClientHandler(clientSocket, out)).start();
                } else {
                    System.out.println("Maximum number of clients reached. Cannot accept more connections.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final BufferedReader in;
        private final PrintWriter out;

        public ClientHandler(Socket clientSocket, PrintWriter out) {
            this.clientSocket = clientSocket;
            this.out = out;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException("Error creating input stream for client", e);
            }
        }

        @Override
        public void run() {
            try {
                launchGame(); // Launch the game for this client

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received from client " + clientSocket + ": " + message);

                    // Broadcast the message to all connected clients
                    Server.getInstance().broadcast("Client " + clientSocket + ": " + message);

                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                }

                in.close();
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void launchGame() {
            // Launch the game in a separate window
            Main.launchGame();
        }
    }

    private static final Server instance = new Server(15); // Default maximum number of clients is 15

    public static Server getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        instance.run();
    }
}