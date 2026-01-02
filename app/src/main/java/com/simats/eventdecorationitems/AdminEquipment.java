package com.simats.eventdecorationitems;

public class AdminEquipment {
    private String id;
    private String name;
    private String category;
    private String subCategory;
    private String specifications;
    private String status;
    private String price;
    private int imageResId;

    public AdminEquipment(String id, String name, String category, String subCategory, String specifications, String status, String price, int imageResId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.specifications = specifications;
        this.status = status;
        this.price = price;
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

    public String getSubCategory() {
        return subCategory;
    }

    public String getSpecifications() {
        return specifications;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
