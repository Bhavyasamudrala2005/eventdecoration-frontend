package com.simats.eventdecorationitems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.itemName.setText(cartItem.getItemName());
        holder.itemPrice.setText(cartItem.getItemPrice());
        holder.itemImage.setImageResource(cartItem.getItemImage());
        holder.quantityText.setText(String.valueOf(cartItem.getQuantity()));
        holder.durationText.setText(String.valueOf(cartItem.getDuration()));

        holder.deleteIcon.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                Cart.getInstance().removeItem(cartItems.get(adapterPosition));
                notifyItemRemoved(adapterPosition);
            }
        });

        holder.quantityPlusButton.setOnClickListener(v -> {
            int quantity = cartItem.getQuantity();
            quantity++;
            cartItem.setQuantity(quantity);
            holder.quantityText.setText(String.valueOf(quantity));
        });

        holder.quantityMinusButton.setOnClickListener(v -> {
            int quantity = cartItem.getQuantity();
            if (quantity > 1) {
                quantity--;
                cartItem.setQuantity(quantity);
                holder.quantityText.setText(String.valueOf(quantity));
            }
        });

        holder.durationPlusButton.setOnClickListener(v -> {
            int duration = cartItem.getDuration();
            duration++;
            cartItem.setDuration(duration);
            holder.durationText.setText(String.valueOf(duration));
        });

        holder.durationMinusButton.setOnClickListener(v -> {
            int duration = cartItem.getDuration();
            if (duration > 1) {
                duration--;
                cartItem.setDuration(duration);
                holder.durationText.setText(String.valueOf(duration));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemPrice;
        ImageView deleteIcon;
        ImageButton quantityMinusButton;
        TextView quantityText;
        ImageButton quantityPlusButton;
        ImageButton durationMinusButton;
        TextView durationText;
        ImageButton durationPlusButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            deleteIcon = itemView.findViewById(R.id.delete_icon);
            quantityMinusButton = itemView.findViewById(R.id.quantity_minus_button);
            quantityText = itemView.findViewById(R.id.quantity_text);
            quantityPlusButton = itemView.findViewById(R.id.quantity_plus_button);
            durationMinusButton = itemView.findViewById(R.id.duration_minus_button);
            durationText = itemView.findViewById(R.id.duration_text);
            durationPlusButton = itemView.findViewById(R.id.duration_plus_button);
        }
    }
}
