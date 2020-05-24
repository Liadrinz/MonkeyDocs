package com.monkey.entity;

public class Message {
    public String type;
    public Delta payload;
    public Delta old;
    public String optional;

    public Message() {
    }

    public Message(String type, Delta payload, Delta old, String optional) {
        this.type = type;
        this.payload = payload;
        this.old = old;
        this.optional = optional;
    }
}
