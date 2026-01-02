package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class EquipmentDetailsResponse {
    
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("equipment")
    private EquipmentDetails equipment;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public EquipmentDetails getEquipment() {
        return equipment;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }

    public static class EquipmentDetails {
        @SerializedName("id")
        private int id;
        
        @SerializedName("name")
        private String name;
        
        @SerializedName("description")
        private String description;
        
        @SerializedName("price")
        private double price;
        
        @SerializedName("stock")
        private int stock;
        
        @SerializedName("category")
        private String category;
        
        @SerializedName("image_url")
        private String imageUrl;

        public int getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }
        public String getCategory() { return category; }
        public String getImageUrl() { return imageUrl; }
    }
}
