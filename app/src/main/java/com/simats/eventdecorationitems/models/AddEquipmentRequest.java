package com.simats.eventdecorationitems.models;

public class AddEquipmentRequest {
    private String name;
    private String category;
    private String type;
    private String specifications;
    private double price_per_day;
    private int quantity;
    private String availability;

    public AddEquipmentRequest(String name, String category, String type, String specifications, double price_per_day, int quantity, String availability) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.specifications = specifications;
        this.price_per_day = price_per_day;
        this.quantity = quantity;
        this.availability = availability;
    }
}
