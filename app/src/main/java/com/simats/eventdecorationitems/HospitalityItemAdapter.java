package com.simats.eventdecorationitems;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HospitalityItemAdapter extends RecyclerView.Adapter<HospitalityItemAdapter.HospitalityItemViewHolder> {

    private List<HospitalityItem> hospitalityItemList;

    public HospitalityItemAdapter(List<HospitalityItem> hospitalityItemList) {
        this.hospitalityItemList = hospitalityItemList;
    }

    @NonNull
    @Override
    public HospitalityItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospitality, parent, false);
        return new HospitalityItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalityItemViewHolder holder, int position) {
        HospitalityItem hospitalityItem = hospitalityItemList.get(position);
        holder.hospitalityItemName.setText(hospitalityItem.getName());
        holder.hospitalityItemType.setText(hospitalityItem.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", hospitalityItem.getRating(), hospitalityItem.getReviews()));
        holder.availabilityStatus.setText(hospitalityItem.getAvailability());
        holder.price.setText(hospitalityItem.getPrice());

        if ("Banquet Tables".equals(hospitalityItem.getName())) {
            holder.hospitalityItemImage.setImageResource(R.drawable.ic_banquet_tables);
        } else if ("Stage Platform".equals(hospitalityItem.getName())) {
            holder.hospitalityItemImage.setImageResource(R.drawable.ic_stage_platform);
        } else if ("Event Curtains".equals(hospitalityItem.getName())) {
            holder.hospitalityItemImage.setImageResource(R.drawable.ic_event_curtains);
        }

        holder.viewDetailsButton.setOnClickListener(v -> {
            if (hospitalityItem.getName().equals("Banquet Tables")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BanquetTablesActivity.class);
                context.startActivity(intent);
            } else if (hospitalityItem.getName().equals("Stage Platform")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, StagePlatformActivity.class);
                context.startActivity(intent);
            } else if (hospitalityItem.getName().equals("Event Curtains")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EventCurtainsActivity.class);
                context.startActivity(intent);
            } else {
                // Handle other hospitality item types here
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalityItemList.size();
    }

    public static class HospitalityItemViewHolder extends RecyclerView.ViewHolder {
        ImageView hospitalityItemImage;
        TextView hospitalityItemName;
        TextView hospitalityItemType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;
        Button viewDetailsButton;

        public HospitalityItemViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalityItemImage = itemView.findViewById(R.id.hospitality_item_image);
            hospitalityItemName = itemView.findViewById(R.id.hospitality_item_name);
            hospitalityItemType = itemView.findViewById(R.id.hospitality_item_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
        }
    }
}
