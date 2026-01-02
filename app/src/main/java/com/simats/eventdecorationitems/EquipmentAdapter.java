package com.simats.eventdecorationitems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    private List<Equipment> equipmentList;

    public EquipmentAdapter(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipment, parent, false);
        return new EquipmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        Equipment equipment = equipmentList.get(position);
        holder.equipmentId.setText(equipment.getId());
        holder.equipmentName.setText(equipment.getName());
        holder.price.setText(equipment.getPrice());

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public static class EquipmentViewHolder extends RecyclerView.ViewHolder {
        ImageView equipmentImage;
        TextView equipmentId;
        TextView equipmentName;
        TextView price;

        public EquipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            equipmentImage = itemView.findViewById(R.id.equipment_image);
            equipmentId = itemView.findViewById(R.id.equipment_id);
            equipmentName = itemView.findViewById(R.id.equipment_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
