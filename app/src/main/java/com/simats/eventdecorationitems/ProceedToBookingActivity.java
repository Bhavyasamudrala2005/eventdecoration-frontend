package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ProceedToBookingActivity extends AppCompatActivity {

    private int quantity;
    private int duration;
    private int pricePerDay;

    private TextView quantityText;
    private TextView itemsSelectedCount;
    private TextView totalItemPrice;
    private TextView subtotalAmount;
    private TextView totalAmountValue;
    private TextView itemPricePerDay;
    private TextView durationText;
    private TextView itemName;
    private ImageView itemImage;
    private CardView itemImageCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed_to_booking);

        // Get data from intent
        Intent intent = getIntent();
        String receivedItemName = intent.getStringExtra("itemName");
        pricePerDay = intent.getIntExtra("itemPrice", 0);
        int receivedItemImage = intent.getIntExtra("itemImage", R.drawable.ic_equipment_placeholder);
        quantity = intent.getIntExtra("quantity", 1);
        duration = intent.getIntExtra("duration", 1);

        // Find views
        ImageView backButton = findViewById(R.id.back_button);
        quantityText = findViewById(R.id.quantity_text);
        itemsSelectedCount = findViewById(R.id.items_selected_count);
        totalItemPrice = findViewById(R.id.total_item_price);
        subtotalAmount = findViewById(R.id.subtotal_amount);
        totalAmountValue = findViewById(R.id.total_amount_value);
        itemPricePerDay = findViewById(R.id.item_price_per_day);
        durationText = findViewById(R.id.duration_text);
        itemName = findViewById(R.id.item_name);
        itemImage = findViewById(R.id.item_image);
        itemImageCard = findViewById(R.id.item_image_card);
        ImageButton quantityMinusButton = findViewById(R.id.quantity_minus_button);
        ImageButton quantityPlusButton = findViewById(R.id.quantity_plus_button);
        Button proceedToBookingButton = findViewById(R.id.proceed_to_booking_button);

        // Set item name and image
        itemName.setText(receivedItemName);
        itemImage.setImageResource(receivedItemImage);
        itemImageCard.setCardBackgroundColor(getResources().getColor(android.R.color.black));

        // Set click listeners
        backButton.setOnClickListener(v -> onBackPressed());

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

        proceedToBookingButton.setOnClickListener(v -> {
            Intent bookingIntent = new Intent(ProceedToBookingActivity.this, BookingDetailsActivity.class);
            bookingIntent.putExtra("itemName", receivedItemName);
            bookingIntent.putExtra("itemImage", receivedItemImage);
            bookingIntent.putExtra("quantity", quantity);
            bookingIntent.putExtra("duration", duration);
            bookingIntent.putExtra("pricePerDay", pricePerDay);
            startActivity(bookingIntent);
        });

        // Initial price update
        updatePrice();
    }

    private void updatePrice() {
        int totalPrice = pricePerDay * quantity * duration;

        quantityText.setText(String.valueOf(quantity));
        itemsSelectedCount.setText(quantity + " item(s) selected");
        itemPricePerDay.setText("₹" + pricePerDay + "/day");
        durationText.setText("x " + duration + " day(s)");
        totalItemPrice.setText("₹" + totalPrice);
        subtotalAmount.setText("₹" + totalPrice);
        totalAmountValue.setText("₹" + totalPrice);
    }
}
