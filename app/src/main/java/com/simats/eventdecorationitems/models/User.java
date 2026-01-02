package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class User {
    private int id;
    @SerializedName("user_id")
    private String userId;
    private String name;
    private String email;
    private String phone;

    // Getters
    public int getId() { return id; }
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
