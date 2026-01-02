package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookingConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        TextView equipmentValue = findViewById(R.id.equipment_value);
        TextView durationValue = findViewById(R.id.duration_value);
        TextView amountPaidValue = findViewById(R.id.amount_paid_value);
        TextView startDateValue = findViewById(R.id.start_date_value);
        Button downloadReceiptButton = findViewById(R.id.download_receipt_button);
        TextView trackBookingButton = findViewById(R.id.track_booking_button);
        TextView backToHomeButton = findViewById(R.id.back_to_home_button);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        int duration = intent.getIntExtra("duration", 1);
        int totalPrice = intent.getIntExtra("totalPrice", 0);
        String startDate = intent.getStringExtra("startDate");

        equipmentValue.setText(itemName);
        durationValue.setText(duration + " day(s)");
        amountPaidValue.setText("₹" + totalPrice);
        startDateValue.setText(startDate);

        trackBookingButton.setOnClickListener(v -> {
            Intent trackBookingIntent = new Intent(BookingConfirmationActivity.this, TrackBookingActivity.class);
            startActivity(trackBookingIntent);
        });

        backToHomeButton.setOnClickListener(v -> {
            Intent homeIntent = new Intent(BookingConfirmationActivity.this, UserDashboardActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
        });
    }
}
