package com.monkey.entity;

public class Message {
    public String type;
    public Delta payload;
    public String optional;

    public Message() {
    }

    public Message(String type, Delta payload, String optional) {
        this.type = type;
        this.payload = payload;
        this.optional = optional;
    }
}
