package com.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class DropMessage {

    public static String createSpawnDrop(double x, double y) {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("type", "spawn")
                .add("x", x)
                .add("y", y);
        JsonObject json = builder.build();
        return json.toString();
    }
}
