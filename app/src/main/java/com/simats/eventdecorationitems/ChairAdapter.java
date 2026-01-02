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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChairAdapter extends RecyclerView.Adapter<ChairAdapter.ChairViewHolder> {

    private List<Chair> chairList;

    public ChairAdapter(List<Chair> chairList) {
        this.chairList = chairList;
    }

    @NonNull
    @Override
    public ChairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chair, parent, false);
        return new ChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChairViewHolder holder, int position) {
        Chair chair = chairList.get(position);
        holder.chairName.setText(chair.getName());
        holder.chairType.setText(chair.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", chair.getRating(), chair.getReviews()));
        holder.availabilityStatus.setText(chair.getAvailability());
        holder.price.setText(chair.getPrice());

        if ("Plastic Chairs".equals(chair.getName())) {
            holder.chairImage.setImageResource(R.drawable.ic_plastic_chair);
        } else if ("Wedding Chairs".equals(chair.getName())) {
            holder.chairImage.setImageResource(R.drawable.ic_wedding_chairs);
        } else if ("VIP Cushioned Chairs".equals(chair.getName())) {
            holder.chairImage.setImageResource(R.drawable.ic_vip_chairs);
        }

        holder.viewDetailsButton.setOnClickListener(v -> {
            if (chair.getName().equals("Plastic Chairs")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, PlasticChairsActivity.class);
                context.startActivity(intent);
            } else if (chair.getName().equals("Wedding Chairs")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, WeddingChairsActivity.class);
                context.startActivity(intent);
            } else if (chair.getName().equals("VIP Cushioned Chairs")) {
                Context context = v.getContext();
                Intent intent = new Intent(context, VipCushionedChairsActivity.class);
                context.startActivity(intent);
            } else {
                // Handle other chair types here
            }
        });
    }

    @Override
    public int getItemCount() {
        return chairList.size();
    }

    public static class ChairViewHolder extends RecyclerView.ViewHolder {
        CardView chairCard;
        ImageView chairImage;
        TextView chairName;
        TextView chairType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;
        Button viewDetailsButton;

        public ChairViewHolder(@NonNull View itemView) {
            super(itemView);
            chairCard = itemView.findViewById(R.id.chair_card);
            chairImage = itemView.findViewById(R.id.chair_image);
            chairName = itemView.findViewById(R.id.chair_name);
            chairType = itemView.findViewById(R.id.chair_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
        }
    }
}
