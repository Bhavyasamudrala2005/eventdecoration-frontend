package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinalBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_booking);

        String itemName = getIntent().getStringExtra("itemName");
        int quantity = getIntent().getIntExtra("quantity", 0);
        int duration = getIntent().getIntExtra("duration", 0);
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);

        TextView itemNameTextView = findViewById(R.id.item_name);
        TextView quantityTextView = findViewById(R.id.item_quantity);
        TextView durationTextView = findViewById(R.id.item_duration);
        TextView totalPriceTextView = findViewById(R.id.total_price);

        itemNameTextView.setText(itemName);
        quantityTextView.setText(String.valueOf(quantity));
        durationTextView.setText(duration + " day(s)");
        totalPriceTextView.setText("₹" + totalPrice);
    }
}
