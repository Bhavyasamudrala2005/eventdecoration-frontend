package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class DashboardStatsResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("total_items")
    private int totalItems;
    
    @SerializedName("total_bookings")
    private int totalBookings;
    
    @SerializedName("pending_approvals")
    private int pendingApprovals;
    
    @SerializedName("low_stock_alerts")
    private int lowStockAlerts;
    
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public int getPendingApprovals() {
        return pendingApprovals;
    }

    public int getLowStockAlerts() {
        return lowStockAlerts;
    }

    public String getMessage() {
        return message;
    }
}
