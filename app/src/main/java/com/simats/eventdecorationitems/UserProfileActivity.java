package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        TextView editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        TextView bookingHistory = findViewById(R.id.booking_history);
        bookingHistory.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, BookingHistoryActivity.class);
            startActivity(intent);
        });

        TextView orderHistory = findViewById(R.id.order_history);
        orderHistory.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });

        TextView notifications = findViewById(R.id.notifications);
        notifications.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
