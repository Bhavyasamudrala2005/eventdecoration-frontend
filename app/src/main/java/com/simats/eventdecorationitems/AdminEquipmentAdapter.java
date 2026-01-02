package com.simats.eventdecorationitems;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class AdminEquipmentAdapter extends RecyclerView.Adapter<AdminEquipmentAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Equipment> equipmentList;

    public AdminEquipmentAdapter(Context context, ArrayList<Equipment> equipmentList) {
        this.context = context;
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_equipment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Equipment equipment = equipmentList.get(position);

        holder.equipmentImage.setImageResource(equipment.getImageResId());
        holder.equipmentId.setText("ID: " + equipment.getId());
        holder.equipmentName.setText(equipment.getName());
        holder.equipmentCategory.setText(String.format("%s • %s", equipment.getCategory(), equipment.getType()));
        holder.equipmentSpecifications.setText(equipment.getSpecifications());
        holder.availabilityStatus.setText(equipment.getAvailability());
        holder.equipmentPrice.setText(equipment.getPrice());

        // UPDATE button click listener
        holder.updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateEquipmentActivity.class);
            intent.putExtra("id", equipment.getId());
            intent.putExtra("name", equipment.getName());
            intent.putExtra("category", equipment.getCategory());
            intent.putExtra("type", equipment.getType());
            intent.putExtra("specifications", equipment.getSpecifications());
            intent.putExtra("price", equipment.getPriceValue());
            intent.putExtra("availability", equipment.getAvailability());
            context.startActivity(intent);
        });

        // DELETE button click listener
        holder.deleteButton.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                showDeleteConfirmationDialog(currentPosition, equipment.getName());
            }
        });
    }

    private void showDeleteConfirmationDialog(int position, String equipmentName) {
        new AlertDialog.Builder(context)
            .setTitle("Delete Equipment")
            .setMessage("Are you sure you want to delete \"" + equipmentName + "\"?")
            .setPositiveButton("Delete", (dialog, which) -> {
                // Remove item from list
                equipmentList.remove(position);
                // Notify adapter of item removal
                notifyItemRemoved(position);
                // Update positions of remaining items
                notifyItemRangeChanged(position, equipmentList.size());
                
                Toast.makeText(context, equipmentName + " deleted successfully", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            })
            .show();
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView equipmentImage;
        TextView equipmentId, equipmentName, equipmentCategory, equipmentSpecifications, availabilityStatus, equipmentPrice;
        MaterialButton updateButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            equipmentImage = itemView.findViewById(R.id.equipment_image);
            equipmentId = itemView.findViewById(R.id.equipment_id);
            equipmentName = itemView.findViewById(R.id.equipment_name);
            equipmentCategory = itemView.findViewById(R.id.equipment_category);
            equipmentSpecifications = itemView.findViewById(R.id.equipment_specifications);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            equipmentPrice = itemView.findViewById(R.id.equipment_price);
            updateButton = itemView.findViewById(R.id.update_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}

