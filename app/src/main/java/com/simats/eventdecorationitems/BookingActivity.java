package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    private int quantity;
    private int duration;
    private int pricePerDay;
    private String itemName;

    private TextView itemsSelected;
    private TextView quantityTextView;
    private TextView totalPriceTextView;
    private TextView subtotalAmount;
    private TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        quantity = getIntent().getIntExtra("quantity", 1);
        duration = getIntent().getIntExtra("duration", 1);
        pricePerDay = getIntent().getIntExtra("pricePerDay", 700);
        itemName = getIntent().getStringExtra("itemName");

        itemsSelected = findViewById(R.id.items_selected);
        quantityTextView = findViewById(R.id.item_quantity);
        TextView durationTextView = findViewById(R.id.item_duration);
        TextView pricePerDayTextView = findViewById(R.id.price_per_day);
        totalPriceTextView = findViewById(R.id.total_price);
        subtotalAmount = findViewById(R.id.subtotal_amount);
        totalAmount = findViewById(R.id.total_amount);
        TextView itemNameTextView = findViewById(R.id.item_name);
        ImageButton plusButton = findViewById(R.id.plus_button);
        ImageButton minusButton = findViewById(R.id.minus_button);
        Button proceedToBookingButton = findViewById(R.id.proceed_to_booking_button);

        durationTextView.setText("x " + duration + " day(s)");
        pricePerDayTextView.setText("₹" + pricePerDay + "/day");
        itemNameTextView.setText(itemName);

        updateUI();

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                updateUI();
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    updateUI();
                }
            }
        });

        proceedToBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, BookingDetailsActivity.class);
                intent.putExtra("itemName", itemName);
                intent.putExtra("quantity", quantity);
                intent.putExtra("totalPrice", quantity * duration * pricePerDay);
                startActivity(intent);
            }
        });
    }

    private void updateUI() {
        itemsSelected.setText(quantity + " item(s) selected");
        quantityTextView.setText(String.valueOf(quantity));
        int total = quantity * duration * pricePerDay;
        totalPriceTextView.setText("₹" + total);
        subtotalAmount.setText("₹" + total);
        totalAmount.setText("₹" + total);
    }
}
