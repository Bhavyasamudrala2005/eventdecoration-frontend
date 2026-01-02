package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class UnreadCountResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("unread_count")
    private int unreadCount;

    public String getStatus() {
        return status;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
}
