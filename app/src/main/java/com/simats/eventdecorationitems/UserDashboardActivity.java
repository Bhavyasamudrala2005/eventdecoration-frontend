package com.simats.eventdecorationitems;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.simats.eventdecorationitems.models.GetNotificationsRequest;
import com.simats.eventdecorationitems.models.UnreadCountResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDashboardActivity extends AppCompatActivity {

    private static final String TAG = "UserDashboard";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        // Click Listeners for category cards
        CardView speakersCard = findViewById(R.id.speakers_card);
        speakersCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, SpeakersActivity.class);
            startActivity(intent);
        });

        CardView tentsCard = findViewById(R.id.tents_card);
        tentsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, TentsActivity.class);
            startActivity(intent);
        });

        CardView carpetsCard = findViewById(R.id.carpets_card);
        carpetsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, CarpetsActivity.class);
            startActivity(intent);
        });

        CardView cookingVesselsCard = findViewById(R.id.cooking_vessels_card);
        cookingVesselsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, CookingVesselsActivity.class);
            startActivity(intent);
        });

        CardView chairsCard = findViewById(R.id.chairs_card);
        chairsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, ChairsActivity.class);
            startActivity(intent);
        });

        CardView hospitalityItemsCard = findViewById(R.id.hospitality_items_card);
        hospitalityItemsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, HospitalityItemsActivity.class);
            startActivity(intent);
        });

        CardView decorationItemsCard = findViewById(R.id.decoration_items_card);
        decorationItemsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, DecorationItemsActivity.class);
            startActivity(intent);
        });

        CardView myBookingsCard = findViewById(R.id.my_bookings_card);
        myBookingsCard.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, BookingHistoryActivity.class);
            startActivity(intent);
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_notifications) {
                    startActivity(new Intent(UserDashboardActivity.this, NotificationActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_cart) {
                    startActivity(new Intent(UserDashboardActivity.this, CartActivity.class));
                    return true;
                } else if (itemId == R.id.navigation_profile) {
                    startActivity(new Intent(UserDashboardActivity.this, UserProfileActivity.class));
                    return true;
                }
                return false;
            }
        });

        // Load unread notification count
        loadUnreadCount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh notification badge when returning to this screen
        loadUnreadCount();
    }

    private void loadUnreadCount() {
        // Get user_id from shared preferences (use 1 as default for testing)
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", 1);

        ApiService apiService = ApiClient.getApiService();
        GetNotificationsRequest request = new GetNotificationsRequest(userId);
        Call<UnreadCountResponse> call = apiService.getUnreadCount(request);

        call.enqueue(new Callback<UnreadCountResponse>() {
            @Override
            public void onResponse(Call<UnreadCountResponse> call, Response<UnreadCountResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UnreadCountResponse data = response.body();
                    
                    if (data.isSuccess()) {
                        int unreadCount = data.getUnreadCount();
                        updateNotificationBadge(unreadCount);
                        Log.d(TAG, "Unread notifications: " + unreadCount);
                    }
                }
            }

            @Override
            public void onFailure(Call<UnreadCountResponse> call, Throwable t) {
                Log.e(TAG, "Failed to get unread count: " + t.getMessage());
                // Silently fail - don't show error to user
            }
        });
    }

    private void updateNotificationBadge(int count) {
        BadgeDrawable badge = bottomNavigationView.getOrCreateBadge(R.id.navigation_notifications);
        
        if (count > 0) {
            badge.setVisible(true);
            badge.setNumber(count);
            badge.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            badge.setVisible(false);
            badge.clearNumber();
        }
    }
}

