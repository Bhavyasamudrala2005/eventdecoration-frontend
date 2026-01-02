package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class BookingApprovalResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
}
