package com.example;

import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;

public class GameServer {
    public static void main(String[] args) {
        String portEnv = System.getenv("PORT");
        int port = (portEnv != null) ? Integer.parseInt(portEnv) : 8080;

        Server server = new Server("0.0.0.0", port, "/", null, GameEndpoint.class);

        try {
            server.start();
            System.out.println("Server started on ws://0.0.0.0:" + port + "/game");
        //    System.out.println("Press Enter to stop the server...");
        //    System.in.read(); // Wait until user presses Enter
            Thread.currentThread().join(); // Keeps the server alive

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
