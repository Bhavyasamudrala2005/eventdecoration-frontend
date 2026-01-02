package com.simats.eventdecorationitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simats.eventdecorationitems.models.NotificationsResponse.NotificationItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private final Context context;
    private final List<NotificationItem> notifications;

    public NotificationAdapter(Context context, List<NotificationItem> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationItem notification = notifications.get(position);

        // Set notification type badge
        String type = notification.getType();
        holder.notificationType.setText(formatType(type));
        
        // Set type badge color based on type
        if ("booking".equals(type)) {
            holder.notificationType.setBackgroundResource(R.drawable.button_accept);
        } else if ("new_equipment".equals(type)) {
            holder.notificationType.setBackgroundResource(R.drawable.button_reject);
        } else {
            holder.notificationType.setBackgroundResource(R.drawable.badge_pending);
        }

        // Set message
        holder.notificationMessage.setText(notification.getMessage());

        // Set time
        holder.notificationTime.setText(getTimeAgo(notification.getCreatedAt()));

        // Show/hide unread indicator
        holder.unreadIndicator.setVisibility(notification.isUnread() ? View.VISIBLE : View.GONE);
    }

    private String formatType(String type) {
        if (type == null) return "Notification";
        switch (type) {
            case "booking":
                return "Booking";
            case "admin":
                return "Admin";
            case "new_equipment":
                return "New Equipment";
            default:
                return "Notification";
        }
    }

    private String getTimeAgo(String dateString) {
        if (dateString == null) return "";
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date = sdf.parse(dateString);
            if (date == null) return dateString;
            
            long diffInMillis = System.currentTimeMillis() - date.getTime();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
            long hours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
            long days = TimeUnit.MILLISECONDS.toDays(diffInMillis);
            
            if (minutes < 1) {
                return "Just now";
            } else if (minutes < 60) {
                return minutes + " min ago";
            } else if (hours < 24) {
                return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
            } else if (days < 7) {
                return days + " day" + (days > 1 ? "s" : "") + " ago";
            } else {
                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
                return outputFormat.format(date);
            }
        } catch (ParseException e) {
            return dateString;
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView notificationType, notificationMessage, notificationTime;
        ImageView notificationIcon;
        View unreadIndicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationType = itemView.findViewById(R.id.notification_type);
            notificationMessage = itemView.findViewById(R.id.notification_message);
            notificationTime = itemView.findViewById(R.id.notification_time);
            notificationIcon = itemView.findViewById(R.id.notification_icon);
            unreadIndicator = itemView.findViewById(R.id.unread_indicator);
        }
    }
}
