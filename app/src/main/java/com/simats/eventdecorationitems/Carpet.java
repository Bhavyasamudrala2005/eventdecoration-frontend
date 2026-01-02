package com.simats.eventdecorationitems;

public class Carpet {
    private String name;
    private String type;
    private double rating;
    private int reviews;
    private String availability;
    private String price;
    private int image;

    public Carpet(String name, String type, double rating, int reviews, String availability, String price, int image) {
        this.name = name;
        this.type = type;
        this.rating = rating;
        this.reviews = reviews;
        this.availability = availability;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getRating() {
        return rating;
    }

    public int getReviews() {
        return reviews;
    }

    public String getAvailability() {
        return availability;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}