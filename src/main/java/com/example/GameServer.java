package com.example;

import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;

public class GameServer {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/", null,
                Collections.singleton(GameEndpoint.class));

        try {
            server.start();
            System.out.println("Server started on ws://localhost:8080/game");
            System.out.println("Press Enter to stop the server...");
            System.in.read(); // Wait until user presses Enter
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
