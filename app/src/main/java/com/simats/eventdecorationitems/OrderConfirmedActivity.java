package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);

        Intent intent = getIntent();
        int totalAmount = intent.getIntExtra("totalAmount", 0);
        int duration = intent.getIntExtra("duration", 1);

        TextView amountPaidText = findViewById(R.id.amount_paid_text);
        TextView durationText = findViewById(R.id.duration_text);

        amountPaidText.setText("₹" + totalAmount);
        durationText.setText(duration + " day(s)");

        Button trackBookingButton = findViewById(R.id.track_booking_button);
        trackBookingButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(OrderConfirmedActivity.this, TrackBookingActivity.class);
            newIntent.putExtra("totalAmount", totalAmount);
            newIntent.putExtra("duration", duration);
            startActivity(newIntent);
        });

        TextView backToHomeButton = findViewById(R.id.back_to_home_button);
        backToHomeButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(OrderConfirmedActivity.this, UserDashboardActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newIntent);
            finish();
        });
    }
}
