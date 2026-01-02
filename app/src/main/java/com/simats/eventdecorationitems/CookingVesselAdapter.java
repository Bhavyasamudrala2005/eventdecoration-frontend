package com.simats.eventdecorationitems;

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

public class CookingVesselAdapter extends RecyclerView.Adapter<CookingVesselAdapter.CookingVesselViewHolder> {

    private List<CookingVessel> cookingVesselList;

    public CookingVesselAdapter(List<CookingVessel> cookingVesselList) {
        this.cookingVesselList = cookingVesselList;
    }

    @NonNull
    @Override
    public CookingVesselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cooking_vessel, parent, false);
        return new CookingVesselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CookingVesselViewHolder holder, int position) {
        CookingVessel cookingVessel = cookingVesselList.get(position);
        holder.vesselName.setText(cookingVessel.getName());
        holder.vesselType.setText(cookingVessel.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", cookingVessel.getRating(), cookingVessel.getReviews()));
        holder.availabilityStatus.setText(cookingVessel.getAvailability());
        holder.price.setText(cookingVessel.getPrice());

        if ("Large Cooking Vessels".equals(cookingVessel.getName())) {
            holder.vesselImage.setImageResource(R.drawable.ic_large_cooking_vessels);
        } else if ("Gas Stoves".equals(cookingVessel.getName())) {
            holder.vesselImage.setImageResource(R.drawable.ic_gas_stove);
        } else if ("Serving Pots".equals(cookingVessel.getName())) {
            holder.vesselImage.setImageResource(R.drawable.ic_serving_pots);
        }

        if (cookingVessel.getAvailability().equals("Available")) {
            holder.availabilityStatus.setBackgroundResource(R.drawable.background_availability_available);
            holder.availabilityStatus.setTextColor(0xFF4CAF50);
        } else {
            holder.availabilityStatus.setBackgroundResource(R.drawable.background_availability_limited);
            holder.availabilityStatus.setTextColor(0xFFFFC107);
        }

        holder.viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cookingVessel.getName().equals("Large Cooking Vessels")) {
                    Intent intent = new Intent(v.getContext(), LargeCookingVesselsActivity.class);
                    v.getContext().startActivity(intent);
                } else if (cookingVessel.getName().equals("Gas Stoves")) {
                    Intent intent = new Intent(v.getContext(), GasStovesActivity.class);
                    v.getContext().startActivity(intent);
                } else if (cookingVessel.getName().equals("Serving Pots")) {
                    Intent intent = new Intent(v.getContext(), ServingPotsActivity.class);
                    v.getContext().startActivity(intent);
                } else {
                    Cart.getInstance().addToCart(new CartItem(cookingVessel.getName(), cookingVessel.getPrice(), cookingVessel.getImage()));
                    Intent intent = new Intent(v.getContext(), CartActivity.class);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cookingVesselList.size();
    }

    public static class CookingVesselViewHolder extends RecyclerView.ViewHolder {
        ImageView vesselImage;
        TextView vesselName;
        TextView vesselType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;
        Button viewDetailsButton;

        public CookingVesselViewHolder(@NonNull View itemView) {
            super(itemView);
            vesselImage = itemView.findViewById(R.id.vessel_image);
            vesselName = itemView.findViewById(R.id.vessel_name);
            vesselType = itemView.findViewById(R.id.vessel_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
        }
    }
}
