package com.simats.eventdecorationitems;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simats.eventdecorationitems.models.GetNotificationsRequest;
import com.simats.eventdecorationitems.models.NotificationsResponse;
import com.simats.eventdecorationitems.models.NotificationsResponse.NotificationItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = "NotificationActivity";
    
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<NotificationItem> notificationList = new ArrayList<>();
    private ProgressBar progressBar;
    private LinearLayout emptyState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Initialize views
        ImageView backButton = findViewById(R.id.back_button);
        recyclerView = findViewById(R.id.notifications_recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        emptyState = findViewById(R.id.empty_state);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotificationAdapter(this, notificationList);
        recyclerView.setAdapter(adapter);

        // Back button
        backButton.setOnClickListener(v -> onBackPressed());

        // Load notifications
        loadNotifications();
    }

    private void loadNotifications() {
        progressBar.setVisibility(View.VISIBLE);
        emptyState.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        // Get user_id from shared preferences (use 1 as default for testing)
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        int userId = prefs.getInt("user_id", 1);

        ApiService apiService = ApiClient.getApiService();
        GetNotificationsRequest request = new GetNotificationsRequest(userId);
        Call<NotificationsResponse> call = apiService.getNotifications(request);

        call.enqueue(new Callback<NotificationsResponse>() {
            @Override
            public void onResponse(Call<NotificationsResponse> call, Response<NotificationsResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    NotificationsResponse data = response.body();

                    if (data.isSuccess() && data.getNotifications() != null && !data.getNotifications().isEmpty()) {
                        notificationList.clear();
                        notificationList.addAll(data.getNotifications());
                        adapter.notifyDataSetChanged();
                        emptyState.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        Log.d(TAG, "Loaded " + notificationList.size() + " notifications from server");
                    } else {
                        // Show empty state if no notifications
                        emptyState.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                } else {
                    Log.e(TAG, "API response failed: " + response.code());
                    emptyState.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<NotificationsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
                emptyState.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }
}



