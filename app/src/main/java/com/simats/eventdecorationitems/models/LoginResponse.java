package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    private String status;
    private String message;
    private User user;

    // Getters
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public User getUser() { return user; }
}
