package org;

import org.core.server.Server;

/**
 * This is the main class. It is responsible for starting the game.
 *
 * @author Logan Aberanthy
 * @Author MrZombii
 */

@SuppressWarnings({"removal"})
public class Main {
    public static void main(String[] args) {
//        startServer();
        launchGame();
    }

    public static void startServer() {
        // Start the server
        Server server = new Server(15);
        Thread serverThread = new Thread(server, "Server");
        serverThread.start();
    }

    public static void launchGame() {
        // new Old_Minecraft().start();
        CircuitVerse mc = new CircuitVerse();
        CircuitVerse.instance = mc;
        Thread gameThread = new Thread(mc, "CircuitVerse");
        gameThread.start();
    }
}