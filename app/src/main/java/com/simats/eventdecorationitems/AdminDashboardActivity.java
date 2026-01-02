package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.simats.eventdecorationitems.models.DashboardStatsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashboardActivity extends AppCompatActivity {

    private static final String TAG = "AdminDashboard";
    
    private TextView totalItemsValue;
    private TextView totalBookingsValue;
    private TextView pendingApprovalsValue;
    private TextView lowStockValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize stats TextViews
        totalItemsValue = findViewById(R.id.total_items_value);
        totalBookingsValue = findViewById(R.id.total_bookings_value);
        pendingApprovalsValue = findViewById(R.id.pending_approvals_value);
        lowStockValue = findViewById(R.id.low_stock_value);

        ImageView logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CardView addEquipmentCard = findViewById(R.id.add_equipment_card);
        addEquipmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AddEquipmentActivity.class);
                startActivity(intent);
            }
        });

        CardView sendNotificationCard = findViewById(R.id.send_notification_card);
        sendNotificationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, SendNotificationActivity.class);
                startActivity(intent);
            }
        });

        CardView viewEquipmentCard = findViewById(R.id.view_equipment_card);
        viewEquipmentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminViewEquipmentActivity.class);
                startActivity(intent);
            }
        });

        CardView bookingRequestsCard = findViewById(R.id.booking_requests_card);
        bookingRequestsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, BookingRequestsActivity.class);
                startActivity(intent);
            }
        });

        // Load dashboard stats
        loadDashboardStats();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh stats when returning to this activity
        loadDashboardStats();
    }

    private void loadDashboardStats() {
        ApiService apiService = ApiClient.getApiService();
        Call<DashboardStatsResponse> call = apiService.getDashboardStats();

        call.enqueue(new Callback<DashboardStatsResponse>() {
            @Override
            public void onResponse(Call<DashboardStatsResponse> call, Response<DashboardStatsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DashboardStatsResponse stats = response.body();
                    
                    if ("success".equals(stats.getStatus())) {
                        // Update UI with stats
                        totalItemsValue.setText(String.valueOf(stats.getTotalItems()));
                        totalBookingsValue.setText(String.valueOf(stats.getTotalBookings()));
                        pendingApprovalsValue.setText(String.valueOf(stats.getPendingApprovals()));
                        lowStockValue.setText(String.valueOf(stats.getLowStockAlerts()));
                        
                        Log.d(TAG, "Dashboard stats loaded successfully");
                    } else {
                        Log.e(TAG, "Failed to load stats: " + stats.getMessage());
                        Toast.makeText(AdminDashboardActivity.this, 
                            "Failed to load stats", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Response not successful: " + response.code());
                    Toast.makeText(AdminDashboardActivity.this, 
                        "Error loading dashboard stats", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashboardStatsResponse> call, Throwable t) {
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
                Toast.makeText(AdminDashboardActivity.this, 
                    "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

