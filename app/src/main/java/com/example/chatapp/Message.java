package com.example.chatapp;

public class Message {
    private String date ,name, msg ;

    public Message(String date, String name, String msg) {
        this.date = date;
        this.name = name;
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }
}
