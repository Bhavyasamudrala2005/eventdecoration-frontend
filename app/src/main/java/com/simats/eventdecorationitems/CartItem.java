package com.simats.eventdecorationitems;

import java.util.Objects;

public class CartItem {
    private String itemName;
    private String itemPrice;
    private int itemImage;
    private int quantity;
    private int duration;

    public CartItem(String itemName, String itemPrice, int itemImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.quantity = 1; // Default quantity is 1
        this.duration = 1; // Default duration is 1
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getItemImage() {
        return itemImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return itemImage == cartItem.itemImage &&
                quantity == cartItem.quantity &&
                duration == cartItem.duration &&
                Objects.equals(itemName, cartItem.itemName) &&
                Objects.equals(itemPrice, cartItem.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemPrice, itemImage, quantity, duration);
    }
}