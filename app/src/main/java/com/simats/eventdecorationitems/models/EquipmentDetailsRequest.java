package com.simats.eventdecorationitems.models;

import com.google.gson.annotations.SerializedName;

public class EquipmentDetailsRequest {
    
    @SerializedName("equipment_id")
    private int equipmentId;

    public EquipmentDetailsRequest(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }
}
