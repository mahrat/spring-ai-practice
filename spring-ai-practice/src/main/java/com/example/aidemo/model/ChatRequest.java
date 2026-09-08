package com.example.aidemo.model;

public class ChatRequest {

    private String messge;
    private String userId;

    public ChatRequest() {
    }

    public ChatRequest(String messge, String userId) {
        this.messge = messge;
        this.userId = userId;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
