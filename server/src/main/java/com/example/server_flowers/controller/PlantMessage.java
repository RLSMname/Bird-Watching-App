package com.example.server_flowers.controller;

import com.example.server_flowers.model.Bird;

public class PlantMessage {
    private String type; // CREATE, UPDATE, DELETE
    private Bird data; // The plant data (for CREATE and UPDATE)

    // Getters and Setters

    public PlantMessage(String type, Bird data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bird getData() {
        return data;
    }

    public void setData(Bird data) {
        this.data = data;
    }
}
