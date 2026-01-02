package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class BookingApprovalRequest {
    
    @SerializedName("booking_id")
    private int bookingId;
    
    @SerializedName("action")
    private String action;

    public BookingApprovalRequest(int bookingId, String action) {
        this.bookingId = bookingId;
        this.action = action;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
