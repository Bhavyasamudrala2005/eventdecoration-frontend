package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class GetNotificationsRequest {
    
    @SerializedName("user_id")
    private int userId;

    public GetNotificationsRequest(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
