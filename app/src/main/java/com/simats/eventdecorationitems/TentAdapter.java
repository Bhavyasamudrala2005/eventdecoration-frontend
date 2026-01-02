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

public class TentAdapter extends RecyclerView.Adapter<TentAdapter.TentViewHolder> {

    private List<Tent> tentList;

    public TentAdapter(List<Tent> tentList) {
        this.tentList = tentList;
    }

    @NonNull
    @Override
    public TentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tent, parent, false);
        return new TentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TentViewHolder holder, int position) {
        Tent tent = tentList.get(position);
        holder.tentName.setText(tent.getName());
        holder.tentType.setText(tent.getType());
        holder.ratingText.setText(String.format("%.1f • %d reviews", tent.getRating(), tent.getReviews()));
        holder.availabilityStatus.setText(tent.getAvailability());
        holder.price.setText(tent.getPrice());

        if ("Wedding Tent".equals(tent.getName())) {
            holder.tentImage.setImageResource(R.drawable.ic_wedding_tent);
        } else if ("Canopy Tent".equals(tent.getName())) {
            holder.tentImage.setImageResource(R.drawable.ic_canopy_tent);
        } else if ("Small Shade Tent".equals(tent.getName())) {
            holder.tentImage.setImageResource(R.drawable.ic_small_shade_tent);
        }

        holder.viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tent.getName().equals("Canopy Tent")) {
                    Intent intent = new Intent(v.getContext(), CanopyTentActivity.class);
                    v.getContext().startActivity(intent);
                } else if (tent.getName().equals("Small Shade Tent")) {
                    Intent intent = new Intent(v.getContext(), SmallShadeTentActivity.class);
                    v.getContext().startActivity(intent);
                } else if (tent.getName().equals("Wedding Tent")) {
                    Intent intent = new Intent(v.getContext(), WeddingTentActivity.class);
                    v.getContext().startActivity(intent);
                } else if (tent.getName().equals("Bluetooth Speaker")) {
                    Intent intent = new Intent(v.getContext(), BluetoothSpeakerActivity.class);
                    v.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(v.getContext(), BookingActivity.class);
                    intent.putExtra("itemName", tent.getName());
                    intent.putExtra("itemPrice", tent.getPrice());
                    intent.putExtra("itemImage", tent.getImage());
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tentList.size();
    }

    public static class TentViewHolder extends RecyclerView.ViewHolder {
        ImageView tentImage;
        TextView tentName;
        TextView tentType;
        TextView ratingText;
        TextView availabilityStatus;
        TextView price;
        Button viewDetailsButton;

        public TentViewHolder(@NonNull View itemView) {
            super(itemView);
            tentImage = itemView.findViewById(R.id.tent_image);
            tentName = itemView.findViewById(R.id.tent_name);
            tentType = itemView.findViewById(R.id.tent_type);
            ratingText = itemView.findViewById(R.id.rating_text);
            availabilityStatus = itemView.findViewById(R.id.availability_status);
            price = itemView.findViewById(R.id.price);
            viewDetailsButton = itemView.findViewById(R.id.view_details_button);
        }
    }
}
