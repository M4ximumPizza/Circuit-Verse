package org.primal.main;

import org.craftmine.core.server.Server;

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
        Minecraft mc = new Minecraft();
        Minecraft.instance = mc;
        Thread gameThread = new Thread(mc, "Minecraft");
        gameThread.start();
    }
}