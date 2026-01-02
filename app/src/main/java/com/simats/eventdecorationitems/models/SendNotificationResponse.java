package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class SendNotificationResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("notification_id")
    private int notificationId;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
}
