package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class SendNotificationRequest {
    
    @SerializedName("type")
    private String type;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("send_to_all")
    private boolean sendToAll;
    
    @SerializedName("user_id")
    private Integer userId;

    // Constructor for sending to all users
    public SendNotificationRequest(String type, String message) {
        this.type = type;
        this.message = message;
        this.sendToAll = true;
        this.userId = null;
    }

    // Constructor for sending to specific user
    public SendNotificationRequest(String type, String message, int userId) {
        this.type = type;
        this.message = message;
        this.sendToAll = false;
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSendToAll() {
        return sendToAll;
    }

    public Integer getUserId() {
        return userId;
    }
}
