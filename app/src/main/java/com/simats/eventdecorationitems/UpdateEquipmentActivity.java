package com.simats.eventdecorationitems;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONObject;

public class UpdateEquipmentActivity extends AppCompatActivity {

    private static final String UPDATE_URL = ApiClient.BASE_URL + "admin_equipment.php?action=update";

    private String equipmentId;
    private EditText equipmentNameEditText;
    private AutoCompleteTextView categoryAutoCompleteTextView;
    private EditText typeEditText;
    private EditText specificationsEditText;
    private EditText priceEditText;
    private ChipGroup availabilityChipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_equipment);

        ImageView backButton = findViewById(R.id.back_button);
        TextView equipmentIdLabel = findViewById(R.id.equipment_id_label);
        equipmentNameEditText = findViewById(R.id.equipment_name_edit_text);
        categoryAutoCompleteTextView = findViewById(R.id.category_auto_complete_text_view);
        typeEditText = findViewById(R.id.type_edit_text);
        specificationsEditText = findViewById(R.id.specifications_edit_text);
        priceEditText = findViewById(R.id.price_edit_text);
        availabilityChipGroup = findViewById(R.id.availability_chip_group);
        Button updateEquipmentButton = findViewById(R.id.update_equipment_button);

        // Get the data from the intent
        equipmentId = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String category = getIntent().getStringExtra("category");
        String type = getIntent().getStringExtra("type");
        String specifications = getIntent().getStringExtra("specifications");
        String price = getIntent().getStringExtra("price");
        String availability = getIntent().getStringExtra("availability");

        // Populate the views
        equipmentIdLabel.setText("ID: " + equipmentId);
        equipmentNameEditText.setText(name);
        
        // Set category as read-only (keep original category, don't allow changes)
        categoryAutoCompleteTextView.setText(category, false);
        categoryAutoCompleteTextView.setEnabled(false);
        categoryAutoCompleteTextView.setFocusable(false);
        categoryAutoCompleteTextView.setClickable(false);
        
        typeEditText.setText(type);
        specificationsEditText.setText(specifications);
        priceEditText.setText(price);

        // Set the selected chip
        for (int i = 0; i < availabilityChipGroup.getChildCount(); i++) {
            Chip chip = (Chip) availabilityChipGroup.getChildAt(i);
            if (chip.getText().toString().equalsIgnoreCase(availability)) {
                chip.setChecked(true);
                break;
            }
        }

        backButton.setOnClickListener(v -> onBackPressed());

        updateEquipmentButton.setOnClickListener(v -> updateEquipment());
    }

    private void updateEquipment() {
        String name = equipmentNameEditText.getText().toString().trim();
        String category = categoryAutoCompleteTextView.getText().toString().trim();
        String type = typeEditText.getText().toString().trim();
        String specifications = specificationsEditText.getText().toString().trim();
        String priceStr = priceEditText.getText().toString().trim();

        // Validate fields
        if (name.isEmpty() || category.isEmpty() || type.isEmpty() || specifications.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected availability
        int checkedChipId = availabilityChipGroup.getCheckedChipId();
        if (checkedChipId == -1) {
            Toast.makeText(this, "Please select availability status", Toast.LENGTH_SHORT).show();
            return;
        }

        Chip selectedChip = findViewById(checkedChipId);
        String availability = selectedChip.getText().toString();

        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                Toast.makeText(this, "Price must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate that equipment ID is not empty
        if (equipmentId == null || equipmentId.trim().isEmpty()) {
            Toast.makeText(this, "Invalid equipment ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating equipment...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            JSONObject json = new JSONObject();
            json.put("id", equipmentId.trim());
            json.put("name", name);
            json.put("category", category);
            json.put("type", type);
            json.put("specifications", specifications);
            json.put("price_per_day", price);
            json.put("availability", availability);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    UPDATE_URL,
                    json,
                    response -> {
                        progressDialog.dismiss();
                        String status = response.optString("status", "error");
                        String message = response.optString("message", "Unknown error");

                        if ("success".equals(status)) {
                            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        progressDialog.dismiss();
                        String errorMessage = "Server error";
                        if (error.networkResponse != null) {
                            errorMessage = "Server error: " + error.networkResponse.statusCode;
                        } else if (error.getMessage() != null) {
                            errorMessage = error.getMessage();
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                    }
            );

            Volley.newRequestQueue(this).add(request);

        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
