package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NotificationsResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("notifications")
    private List<NotificationItem> notifications;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<NotificationItem> getNotifications() {
        return notifications;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
    
    // Inner class for notification item
    public static class NotificationItem {
        @SerializedName("id")
        private int id;
        
        @SerializedName("type")
        private String type;
        
        @SerializedName("message")
        private String message;
        
        @SerializedName("status")
        private String status;
        
        @SerializedName("created_at")
        private String createdAt;

        public int getId() { return id; }
        public String getType() { return type; }
        public String getMessage() { return message; }
        public String getStatus() { return status; }
        public String getCreatedAt() { return createdAt; }
        
        public boolean isUnread() {
            return "unread".equals(status);
        }
    }
}
