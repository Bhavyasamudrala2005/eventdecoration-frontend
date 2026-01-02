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

public class DecorationItemAdapter extends RecyclerView.Adapter<DecorationItemAdapter.DecorationItemViewHolder> {

    private List<DecorationItem> decorationItemList;

    public DecorationItemAdapter(List<DecorationItem> decorationItemList) {
        this.decorationItemList = decorationItemList;
    }

    @NonNull
    @Override
    public DecorationItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_decoration, parent, false);
        return new DecorationItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DecorationItemViewHolder holder, int position) {
        DecorationItem decorationItem = decorationItemList.get(position);
        holder.decorationItemName.setText(decorationItem.getName());
        holder.decorationItemType.setText(decorationItem.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", decorationItem.getRating(), decorationItem.getReviews()));
        holder.availabilityStatus.setText(decorationItem.getAvailability());
        holder.price.setText(decorationItem.getPrice());

        if ("LED Lighting System".equals(decorationItem.getName())) {
            holder.decorationItemImage.setImageResource(R.drawable.ic_led_lighting);
        } else if ("Flower Stands".equals(decorationItem.getName())) {
            holder.decorationItemImage.setImageResource(R.drawable.ic_flower_stands);
        } else if ("Backdrop Decor".equals(decorationItem.getName())) {
            holder.decorationItemImage.setImageResource(R.drawable.ic_backdrop_decor);
        } else if ("Floral Arches".equals(decorationItem.getName())) {
            holder.decorationItemImage.setImageResource(R.drawable.ic_floral_arches);
        }

        holder.viewDetailsButton.setOnClickListener(v -> {
            if (decorationItem.getName().equals("LED Lighting System")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, LedLightingSystemActivity.class);
                context.startActivity(intent);
            } else if (decorationItem.getName().equals("Flower Stands")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, FlowerStandsActivity.class);
                context.startActivity(intent);
            } else if (decorationItem.getName().equals("Backdrop Decor")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, BackdropDecorActivity.class);
                context.startActivity(intent);
            } else if (decorationItem.getName().equals("Floral Arches")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, FloralArchesActivity.class);
                context.startActivity(intent);
            } else {
                // Handle other decoration item types here
            }
        });
    }

    @Override
    public int getItemCount() {
        return decorationItemList.size();
    }

    public static class DecorationItemViewHolder extends RecyclerView.ViewHolder {
        ImageView decorationItemImage;
        TextView decorationItemName;
        TextView decorationItemType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;
        Button viewDetailsButton;

        public DecorationItemViewHolder(@NonNull View itemView) {
            super(itemView);
            decorationItemImage = itemView.findViewById(R.id.decoration_item_image);
            decorationItemName = itemView.findViewById(R.id.decoration_item_name);
            decorationItemType = itemView.findViewById(R.id.decoration_item_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
        }
    }
}
