package com.example;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.io.IOException; // also needed for sendText



@ServerEndpoint("/game")
public class GameEndpoint {

    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Client connected: " + session.getId());
        sessions.add(session);


        DropSpawner.start(sessions); // ✅ trigger spawner logic
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received from " + session.getId() + ": " + message);

        // Example: when the client moves, respond with a droplet spawn
        //String response = String.format("{\"type\":\"spawn\",\"x\":%.2f,\"y\":5.0}", Math.random() * 7);

        // for (Session s : sessions) {
        //   if (s.isOpen()) {
        //       try {
        //           s.getBasicRemote().sendText(response); // ✅ Send valid JSON
        //       } catch (IOException e) {
        //           e.printStackTrace();
        //       }
        //   }
        //}
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error in session " + session.getId() + ": " + throwable.getMessage());
    }
}
