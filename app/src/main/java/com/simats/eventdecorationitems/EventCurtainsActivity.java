package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventCurtainsActivity extends AppCompatActivity {

    private int quantity = 1;
    private int duration = 1;
    private final int pricePerDay = 1000;

    private TextView quantityText;
    private TextView durationText;
    private TextView totalAmount;
    private TextView totalAmountCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_curtains);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        quantityText = findViewById(R.id.quantity_text);
        durationText = findViewById(R.id.duration_text);
        totalAmount = findViewById(R.id.total_amount);
        totalAmountCalculation = findViewById(R.id.total_amount_calculation);

        ImageButton quantityMinusButton = findViewById(R.id.quantity_minus_button);
        ImageButton quantityPlusButton = findViewById(R.id.quantity_plus_button);
        ImageButton durationMinusButton = findViewById(R.id.duration_minus_button);
        ImageButton durationPlusButton = findViewById(R.id.duration_plus_button);

        updatePrice();

        quantityMinusButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updatePrice();
            }
        });

        quantityPlusButton.setOnClickListener(v -> {
            quantity++;
            updatePrice();
        });

        durationMinusButton.setOnClickListener(v -> {
            if (duration > 1) {
                duration--;
                updatePrice();
            }
        });

        durationPlusButton.setOnClickListener(v -> {
            duration++;
            updatePrice();
        });

        Button bookNowButton = findViewById(R.id.book_now_button);
        bookNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(EventCurtainsActivity.this, ProceedToBookingActivity.class);
            intent.putExtra("itemName", "Event Curtains");
            intent.putExtra("itemImage", R.drawable.ic_event_curtains);
            intent.putExtra("itemPrice", pricePerDay);
            intent.putExtra("quantity", quantity);
            intent.putExtra("duration", duration);
            startActivity(intent);
        });
    }

    private void updatePrice() {
        int totalPrice = pricePerDay * quantity * duration;
        quantityText.setText(String.valueOf(quantity));
        durationText.setText(String.valueOf(duration));
        totalAmount.setText("₹" + totalPrice);
        totalAmountCalculation.setText(quantity + " item(s) × " + duration + " day(s)");
    }
}
