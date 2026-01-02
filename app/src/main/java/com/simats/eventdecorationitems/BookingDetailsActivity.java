package com.simats.eventdecorationitems;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class BookingDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText startDateEditText;
    private TextInputEditText endDateEditText;
    private CardView morningCard, afternoonCard, eveningCard, fullDayCard;
    private CardView selectedTimeSlot;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        String itemName = getIntent().getStringExtra("itemName");
        int quantity = getIntent().getIntExtra("quantity", 0);
        int pricePerDay = getIntent().getIntExtra("pricePerDay", 0);
        int duration = getIntent().getIntExtra("duration", 0);
        totalPrice = pricePerDay * quantity * duration;

        TextView itemDetails = findViewById(R.id.item_details);
        TextView itemTotalPrice = findViewById(R.id.item_total_price);
        TextView totalAmount = findViewById(R.id.total_amount);
        startDateEditText = findViewById(R.id.start_date_edittext);
        endDateEditText = findViewById(R.id.end_date_edittext);
        morningCard = findViewById(R.id.morning_card);
        afternoonCard = findViewById(R.id.afternoon_card);
        eveningCard = findViewById(R.id.evening_card);
        fullDayCard = findViewById(R.id.full_day_card);
        Button proceedToPaymentButton = findViewById(R.id.proceed_to_payment_button);

        itemDetails.setText(itemName + " x " + quantity);
        itemTotalPrice.setText("₹" + totalPrice);
        totalAmount.setText("₹" + totalPrice);

        startDateEditText.setOnClickListener(this);
        endDateEditText.setOnClickListener(this);
        morningCard.setOnClickListener(this);
        afternoonCard.setOnClickListener(this);
        eveningCard.setOnClickListener(this);
        fullDayCard.setOnClickListener(this);
        proceedToPaymentButton.setOnClickListener(this);
    }

    private void showDatePickerDialog(final TextInputEditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> editText.setText(String.format("%02d/%02d/%d", month1 + 1, dayOfMonth, year1)),
                year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.start_date_edittext) {
            showDatePickerDialog(startDateEditText);
        } else if (id == R.id.end_date_edittext) {
            showDatePickerDialog(endDateEditText);
        } else if (id == R.id.morning_card || id == R.id.afternoon_card || id == R.id.evening_card || id == R.id.full_day_card) {
            selectTimeSlot((CardView) v);
        } else if (id == R.id.proceed_to_payment_button) {
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);
        }
    }

    private void selectTimeSlot(CardView cardView) {
        if (selectedTimeSlot != null) {
            selectedTimeSlot.setCardBackgroundColor(Color.WHITE);
        }
        cardView.setCardBackgroundColor(Color.LTGRAY);
        selectedTimeSlot = cardView;
    }
}
