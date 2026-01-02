package com.simats.eventdecorationitems;

public class Equipment {
    private String id;
    private String name;
    private String category;
    private String type;
    private String specifications;
    private double price;
    private String availability;
    private int imageResId;

    public Equipment(String id, String name, String category, String type, String specifications, double price, String availability, int imageResId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.specifications = specifications;
        this.price = price;
        this.availability = availability;
        this.imageResId = imageResId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSpecifications() {
        return specifications;
    }

    public String getPrice() {
        return String.format("₹%,.0f/day", price);
    }

    public String getPriceValue() {
        return String.valueOf(price);
    }

    public String getAvailability() {
        return availability;
    }

    public int getImageResId() {
        return imageResId;
    }
}
