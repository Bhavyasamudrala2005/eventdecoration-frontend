package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EquipmentListResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("equipment_list")
    private List<EquipmentData> equipmentList;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<EquipmentData> getEquipmentList() {
        return equipmentList;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
    
    // Inner class for equipment data from API
    public static class EquipmentData {
        @SerializedName("id")
        private int id;
        
        @SerializedName("name")
        private String name;
        
        @SerializedName("category")
        private String category;
        
        @SerializedName("type")
        private String type;
        
        @SerializedName("specifications")
        private String specifications;
        
        @SerializedName("price_per_day")
        private double pricePerDay;
        
        @SerializedName("quantity")
        private int quantity;
        
        @SerializedName("availability")
        private String availability;
        
        public int getId() { return id; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getType() { return type; }
        public String getSpecifications() { return specifications; }
        public double getPricePerDay() { return pricePerDay; }
        public int getQuantity() { return quantity; }
        public String getAvailability() { return availability; }
    }
}
