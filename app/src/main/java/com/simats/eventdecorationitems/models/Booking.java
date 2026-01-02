package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class Booking {
    
    @SerializedName("id")
    private int id;
    
    @SerializedName("user_id")
    private int userId;
    
    @SerializedName("user_name")
    private String userName;
    
    @SerializedName("equipment_id")
    private int equipmentId;
    
    @SerializedName("equipment_name")
    private String equipmentName;
    
    @SerializedName("quantity")
    private int quantity;
    
    @SerializedName("rental_days")
    private int rentalDays;
    
    @SerializedName("total_amount")
    private double totalAmount;
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("created_at")
    private String createdAt;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
