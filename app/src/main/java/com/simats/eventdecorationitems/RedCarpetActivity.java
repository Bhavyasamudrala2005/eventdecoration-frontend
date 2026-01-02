package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.simats.eventdecorationitems.models.EquipmentDetailsRequest;
import com.simats.eventdecorationitems.models.EquipmentDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedCarpetActivity extends AppCompatActivity {

    private static final String TAG = "RedCarpetActivity";
    
    // Equipment ID for Red Carpet in database
    private static final int EQUIPMENT_ID = 7; // Red Carpet ID
    
    private int quantity = 1;
    private int duration = 1;
    private int pricePerDay = 8000; // Default price, will be updated from database

    private TextView quantityText;
    private TextView durationText;
    private TextView totalAmount;
    private TextView totalAmountCalculation;
    private TextView pricePerDayText;
    private TextView carpetNameText;
    private TextView specificationsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_carpet);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        quantityText = findViewById(R.id.quantity_text);
        durationText = findViewById(R.id.duration_text);
        totalAmount = findViewById(R.id.total_amount);
        totalAmountCalculation = findViewById(R.id.total_amount_calculation);
        pricePerDayText = findViewById(R.id.price_per_day);
        carpetNameText = findViewById(R.id.carpet_name);
        specificationsDetails = findViewById(R.id.specifications_details);

        ImageButton quantityMinusButton = findViewById(R.id.quantity_minus_button);
        ImageButton quantityPlusButton = findViewById(R.id.quantity_plus_button);
        ImageButton durationMinusButton = findViewById(R.id.duration_minus_button);
        ImageButton durationPlusButton = findViewById(R.id.duration_plus_button);

        // Load equipment details from database
        loadEquipmentDetails();

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
            Intent intent = new Intent(RedCarpetActivity.this, ProceedToBookingActivity.class);
            intent.putExtra("itemName", "Red Carpet");
            intent.putExtra("itemImage", R.drawable.ic_red_carpet);
            intent.putExtra("itemPrice", pricePerDay);
            intent.putExtra("quantity", quantity);
            intent.putExtra("duration", duration);
            intent.putExtra("equipmentId", EQUIPMENT_ID);
            startActivity(intent);
        });
    }

    private void loadEquipmentDetails() {
        ApiService apiService = ApiClient.getApiService();
        EquipmentDetailsRequest request = new EquipmentDetailsRequest(EQUIPMENT_ID);
        Call<EquipmentDetailsResponse> call = apiService.getEquipmentDetails(request);

        call.enqueue(new Callback<EquipmentDetailsResponse>() {
            @Override
            public void onResponse(Call<EquipmentDetailsResponse> call, Response<EquipmentDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EquipmentDetailsResponse data = response.body();
                    
                    if (data.isSuccess() && data.getEquipment() != null) {
                        EquipmentDetailsResponse.EquipmentDetails equipment = data.getEquipment();
                        
                        // Update price from database
                        pricePerDay = (int) equipment.getPrice();
                        pricePerDayText.setText("₹" + pricePerDay);
                        
                        // Update name if needed
                        carpetNameText.setText(equipment.getName());
                        
                        // Update specifications
                        if (equipment.getDescription() != null && !equipment.getDescription().isEmpty()) {
                            specificationsDetails.setText(equipment.getDescription());
                        }
                        
                        // Recalculate total with new price
                        updatePrice();
                        
                        Log.d(TAG, "Loaded equipment: " + equipment.getName() + ", Price: " + pricePerDay);
                    }
                } else {
                    Log.e(TAG, "Failed to load equipment details");
                }
            }

            @Override
            public void onFailure(Call<EquipmentDetailsResponse> call, Throwable t) {
                Log.e(TAG, "Network error: " + t.getMessage());
                // Use default price if network fails
            }
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

