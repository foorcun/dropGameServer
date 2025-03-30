package com.example;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.*;

public class DropSpawner {

    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static boolean started = false;

    public static void start(Set<Session> sessions) {
        if (started) return;
        started = true;

        scheduler.scheduleAtFixedRate(() -> {


            for (Session session : sessions) {
                if (session.isOpen()) {
                    try {
                        String message = DropMessage.createSpawnDrop(Math.random() * 7, 5.0);
                        session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void stop() {
        scheduler.shutdownNow();
        started = false;
    }
}
