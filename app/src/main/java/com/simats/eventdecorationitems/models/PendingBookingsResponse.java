package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PendingBookingsResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("bookings")
    private List<Booking> bookings;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
}
