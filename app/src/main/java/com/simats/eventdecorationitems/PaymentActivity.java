package com.simats.eventdecorationitems;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private int totalPrice;
    private CardView fullPaymentCard, partialPaymentCard, cardPaymentCard, upiPaymentCard, walletPaymentCard, netbankingPaymentCard;
    private CardView selectedPaymentType, selectedPaymentMode;
    private TextView amountToPay;
    private String itemName;
    private int duration;
    private String startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        totalPrice = getIntent().getIntExtra("totalPrice", 0);
        itemName = getIntent().getStringExtra("itemName");
        duration = getIntent().getIntExtra("duration", 1);
        startDate = getIntent().getStringExtra("startDate");

        TextView fullPaymentAmount = findViewById(R.id.full_payment_amount);
        TextView summaryTotalAmount = findViewById(R.id.summary_total_amount);
        amountToPay = findViewById(R.id.summary_amount_to_pay);
        Button confirmBookingButton = findViewById(R.id.confirm_booking_button);

        fullPaymentAmount.setText("₹" + totalPrice);
        summaryTotalAmount.setText("₹" + totalPrice);
        amountToPay.setText("₹" + totalPrice);

        fullPaymentCard = findViewById(R.id.full_payment_card);
        partialPaymentCard = findViewById(R.id.partial_payment_card);
        cardPaymentCard = findViewById(R.id.card_payment_card);
        upiPaymentCard = findViewById(R.id.upi_payment_card);
        walletPaymentCard = findViewById(R.id.wallet_payment_card);
        netbankingPaymentCard = findViewById(R.id.netbanking_payment_card);

        fullPaymentCard.setOnClickListener(this);
        partialPaymentCard.setOnClickListener(this);
        cardPaymentCard.setOnClickListener(this);
        upiPaymentCard.setOnClickListener(this);
        walletPaymentCard.setOnClickListener(this);
        netbankingPaymentCard.setOnClickListener(this);
        confirmBookingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.full_payment_card) {
            selectPaymentType(fullPaymentCard);
            amountToPay.setText("₹" + totalPrice);
        } else if (id == R.id.partial_payment_card) {
            selectPaymentType(partialPaymentCard);
            amountToPay.setText("₹" + (int) (totalPrice * 0.3));
        } else if (id == R.id.card_payment_card || id == R.id.upi_payment_card || id == R.id.wallet_payment_card || id == R.id.netbanking_payment_card) {
            selectPaymentMode((CardView) v);
        } else if (id == R.id.confirm_booking_button) {
            Intent intent = new Intent(this, BookingConfirmationActivity.class);
            intent.putExtra("totalPrice", totalPrice);
            intent.putExtra("itemName", itemName);
            intent.putExtra("duration", duration);
            intent.putExtra("startDate", startDate);
            startActivity(intent);
        }
    }

    private void selectPaymentType(CardView cardView) {
        if (selectedPaymentType != null) {
            selectedPaymentType.setCardBackgroundColor(Color.WHITE);
        }
        cardView.setCardBackgroundColor(Color.LTGRAY);
        selectedPaymentType = cardView;
    }

    private void selectPaymentMode(CardView cardView) {
        if (selectedPaymentMode != null) {
            selectedPaymentMode.setCardBackgroundColor(Color.WHITE);
        }
        cardView.setCardBackgroundColor(Color.LTGRAY);
        selectedPaymentMode = cardView;
    }
}
