package com.example.senejean.logemoi.model;

public class Message {
    private String id_msg;
    private String text;
    private User sender;

    public Message(String id_msg, String text, User sender) {
        this.id_msg = id_msg;
        this.text = text;
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getId_msg() {
        return id_msg;
    }

    public void setId_msg(String id_msg) {
        this.id_msg = id_msg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
