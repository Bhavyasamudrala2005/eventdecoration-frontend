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

public class CarpetAdapter extends RecyclerView.Adapter<CarpetAdapter.CarpetViewHolder> {

    private List<Carpet> carpetList;

    public CarpetAdapter(List<Carpet> carpetList) {
        this.carpetList = carpetList;
    }

    @NonNull
    @Override
    public CarpetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carpet, parent, false);
        return new CarpetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarpetViewHolder holder, int position) {
        Carpet carpet = carpetList.get(position);
        holder.carpetName.setText(carpet.getName());
        holder.carpetType.setText(carpet.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", carpet.getRating(), carpet.getReviews()));
        holder.availabilityStatus.setText(carpet.getAvailability());
        holder.price.setText(carpet.getPrice());

        if ("Red Carpet".equals(carpet.getName())) {
            holder.carpetImage.setImageResource(R.drawable.ic_red_carpet);
        } else if ("Event Carpet".equals(carpet.getName())) {
            holder.carpetImage.setImageResource(R.drawable.ic_event_carpet);
        } else if ("Mandap Carpet".equals(carpet.getName())) {
            holder.carpetImage.setImageResource(R.drawable.ic_mandap_carpet);
        }

        holder.viewDetailsButton.setOnClickListener(v -> {
            if (carpet.getName().equals("Red Carpet")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, RedCarpetActivity.class);
                context.startActivity(intent);
            } else if (carpet.getName().equals("Event Carpet")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, EventCarpetActivity.class);
                context.startActivity(intent);
            } else if (carpet.getName().equals("Mandap Carpet")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MandapCarpetActivity.class);
                context.startActivity(intent);
            } else {
                // Handle other carpet types here
            }
        });
    }

    @Override
    public int getItemCount() {
        return carpetList.size();
    }

    public static class CarpetViewHolder extends RecyclerView.ViewHolder {
        ImageView carpetImage;
        TextView carpetName;
        TextView carpetType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;
        Button viewDetailsButton;

        public CarpetViewHolder(@NonNull View itemView) {
            super(itemView);
            carpetImage = itemView.findViewById(R.id.carpet_image);
            carpetName = itemView.findViewById(R.id.carpet_name);
            carpetType = itemView.findViewById(R.id.carpet_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
        }
    }
}
