package com.simats.eventdecorationitems.models;

public class AddEquipmentResponse {
    private String status;
    private String message;
    private int equipment_id;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getEquipmentId() {
        return equipment_id;
    }

    public boolean isSuccess() {
        return "success".equals(status);
    }
}
