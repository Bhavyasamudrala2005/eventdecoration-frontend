package com.simats.eventdecorationitems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simats.eventdecorationitems.models.Booking;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BookingRequestAdapter extends RecyclerView.Adapter<BookingRequestAdapter.ViewHolder> {

    private List<Booking> bookings;
    private OnBookingActionListener listener;

    public interface OnBookingActionListener {
        void onAccept(Booking booking, int position);
        void onReject(Booking booking, int position);
    }

    public BookingRequestAdapter(List<Booking> bookings, OnBookingActionListener listener) {
        this.bookings = bookings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking_request, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        
        holder.equipmentName.setText(booking.getEquipmentName());
        holder.userName.setText(booking.getUserName() != null ? booking.getUserName() : "User #" + booking.getUserId());
        holder.quantity.setText(String.valueOf(booking.getQuantity()));
        holder.rentalDays.setText(booking.getRentalDays() + " days");
        
        // Format currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        holder.totalAmount.setText(currencyFormat.format(booking.getTotalAmount()));
        
        // Format date if available
        if (booking.getCreatedAt() != null && !booking.getCreatedAt().isEmpty()) {
            holder.createdAt.setText("Requested on: " + booking.getCreatedAt());
        } else {
            holder.createdAt.setVisibility(View.GONE);
        }

        holder.acceptButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAccept(booking, holder.getAdapterPosition());
            }
        });

        holder.rejectButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onReject(booking, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookings != null ? bookings.size() : 0;
    }

    public void removeItem(int position) {
        if (position >= 0 && position < bookings.size()) {
            bookings.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, bookings.size());
        }
    }

    public void updateData(List<Booking> newBookings) {
        this.bookings = newBookings;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView equipmentName;
        TextView userName;
        TextView quantity;
        TextView rentalDays;
        TextView totalAmount;
        TextView createdAt;
        TextView statusBadge;
        Button acceptButton;
        Button rejectButton;

        ViewHolder(View itemView) {
            super(itemView);
            equipmentName = itemView.findViewById(R.id.equipment_name);
            userName = itemView.findViewById(R.id.user_name);
            quantity = itemView.findViewById(R.id.quantity);
            rentalDays = itemView.findViewById(R.id.rental_days);
            totalAmount = itemView.findViewById(R.id.total_amount);
            createdAt = itemView.findViewById(R.id.created_at);
            statusBadge = itemView.findViewById(R.id.status_badge);
            acceptButton = itemView.findViewById(R.id.accept_button);
            rejectButton = itemView.findViewById(R.id.reject_button);
        }
    }
}
