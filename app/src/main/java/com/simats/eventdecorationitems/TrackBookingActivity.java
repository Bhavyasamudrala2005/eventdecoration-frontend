package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrackBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_booking);

        Intent intent = getIntent();
        int totalAmount = intent.getIntExtra("totalAmount", 0);
        int duration = intent.getIntExtra("duration", 1);

        TextView totalAmountText = findViewById(R.id.total_amount);
        TextView itemDurationText = findViewById(R.id.item_duration);

        totalAmountText.setText("₹" + totalAmount);
        itemDurationText.setText("Duration: " + duration + " day(s)\n25/12/2025 - 25/12/2025");

        Button cancelBookingButton = findViewById(R.id.cancel_booking_button);
        cancelBookingButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(TrackBookingActivity.this, UserDashboardActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);
            finish();
        });
    }
}
