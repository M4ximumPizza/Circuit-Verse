package org.core.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * The client class is used to connect to the server and send messages to it.
 * The client will keep sending messages to the server until the user types 'exit'.
 *
 * The client will also print the server's response to the console.
 *
 * @author Logan Abernathy
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String message;

            // Start a new thread to listen for messages from the server
            new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println("Server response: " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Allow the client to send messages to the server
            while (true) {
                System.out.print("Enter message (type 'exit' to close): ");
                message = scanner.nextLine();
                out.println(message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}