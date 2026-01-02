package com.simats.eventdecorationitems;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddEquipmentActivity extends AppCompatActivity {

    private static final String ADD_URL = "https://kc7xphq2-80.inc1.devtunnels.ms/eventease/admin_equipment.php?action=add";

    EditText etName, etType, etSpecs, etPrice, etQty;
    AutoCompleteTextView categoryAutocomplete;
    RadioGroup availabilityGroup;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equipment);

        etName = findViewById(R.id.equipment_name_edit_text);
        categoryAutocomplete = findViewById(R.id.category_autocomplete);
        etType = findViewById(R.id.type_edit_text);
        etSpecs = findViewById(R.id.specifications_edit_text);
        etPrice = findViewById(R.id.price_edit_text);
        etQty = findViewById(R.id.quantity_edit_text);
        availabilityGroup = findViewById(R.id.availability_radio_group);
        
        btnAdd = findViewById(R.id.submit_button);

        // --- CATEGORY SPINNER SETUP ---
        final List<String> categories = new ArrayList<>(Arrays.asList(
                "Audio", "Lighting", "Carpets", "Tents",
                "Cooking Vessels", "Chairs", "Hospitality Items", "Decoration Items"
        ));

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, categories);
        categoryAutocomplete.setAdapter(adapter);
        // --------------------------------

        btnAdd.setOnClickListener(v -> addEquipment());
    }

    private void addEquipment() {

        String name = etName.getText().toString().trim();
        String category = categoryAutocomplete.getText().toString().trim();
        String type = etType.getText().toString().trim();
        String specs = etSpecs.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String qtyStr = etQty.getText().toString().trim();

        if (name.isEmpty() || category.isEmpty() || type.isEmpty() || specs.isEmpty()
                || priceStr.isEmpty() || qtyStr.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int checkedId = availabilityGroup.getCheckedRadioButtonId();
        if (checkedId == -1) {
            Toast.makeText(this, "Select availability", Toast.LENGTH_SHORT).show();
            return;
        }

        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceStr);
            quantity = Integer.parseInt(qtyStr);
        } catch (Exception e) {
            Toast.makeText(this, "Invalid price or quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        String availability;
        if (checkedId == R.id.available_radio_button) {
            availability = "Available";
        } else if (checkedId == R.id.limited_radio_button) {
            availability = "Limited";
        } else {
            availability = "Unavailable";
        }

        // Show progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding equipment...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("category", category);
            json.put("type", type);
            json.put("specifications", specs);
            json.put("price_per_day", price);
            json.put("quantity", quantity);
            json.put("availability", availability);

            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    ADD_URL,
                    json,
                    response -> {
                        progressDialog.dismiss();
                        String status = response.optString("status", "error");
                        String message = response.optString("message", "Equipment added successfully");

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
                        String errorMessage = "An unknown error occurred.";

                        if (error instanceof ParseError && error.networkResponse != null && error.networkResponse.data != null) {
                            try {
                                String rawResponse = new String(error.networkResponse.data, "UTF-8");
                                errorMessage = "Server returned an invalid response. Raw response: " + rawResponse;
                            } catch (UnsupportedEncodingException e) {
                                errorMessage = "Error parsing server's invalid response.";
                            }
                        } else if (error instanceof TimeoutError || error instanceof NetworkError) {
                            errorMessage = "Network error. Please check your internet connection.";
                        } else if (error.networkResponse != null) {
                            errorMessage = "Server error: " + error.networkResponse.statusCode;
                        } else if (error.getMessage() != null) {
                            errorMessage = error.getMessage();
                        }

                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                    }
            );

            Volley.newRequestQueue(this).add(request);

        } catch (JSONException e) {
            progressDialog.dismiss();
            Toast.makeText(this, "Error creating request: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
