package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServingPotsActivity extends AppCompatActivity {

    private int quantity = 1;
    private int duration = 1;
    private int pricePerDay = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serving_pots);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        TextView quantityText = findViewById(R.id.quantity_text);
        ImageButton quantityMinusButton = findViewById(R.id.quantity_minus_button);
        ImageButton quantityPlusButton = findViewById(R.id.quantity_plus_button);

        TextView durationText = findViewById(R.id.duration_text);
        ImageButton durationMinusButton = findViewById(R.id.duration_minus_button);
        ImageButton durationPlusButton = findViewById(R.id.duration_plus_button);

        TextView totalAmount = findViewById(R.id.total_amount);
        TextView totalAmountCalculation = findViewById(R.id.total_amount_calculation);

        quantityMinusButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityText.setText(String.valueOf(quantity));
                updateTotal(totalAmount, totalAmountCalculation);
            }
        });

        quantityPlusButton.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
            updateTotal(totalAmount, totalAmountCalculation);
        });

        durationMinusButton.setOnClickListener(v -> {
            if (duration > 1) {
                duration--;
                durationText.setText(String.valueOf(duration));
                updateTotal(totalAmount, totalAmountCalculation);
            }
        });

        durationPlusButton.setOnClickListener(v -> {
            duration++;
            durationText.setText(String.valueOf(duration));
            updateTotal(totalAmount, totalAmountCalculation);
        });

        Button bookNowButton = findViewById(R.id.book_now_button);
        bookNowButton.setOnClickListener(v -> {
            Intent intent = new Intent(ServingPotsActivity.this, ProceedToBookingActivity.class);
            intent.putExtra("itemName", "Serving Pots");
            intent.putExtra("itemPrice", String.valueOf(pricePerDay * quantity * duration));
            startActivity(intent);
        });

        updateTotal(totalAmount, totalAmountCalculation);
    }

    private void updateTotal(TextView totalAmount, TextView totalAmountCalculation) {
        int total = pricePerDay * quantity * duration;
        totalAmount.setText(String.format("₹%d", total));
        totalAmountCalculation.setText(String.format("%d item(s) x %d day(s)", quantity, duration));
    }
}
