package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simats.eventdecorationitems.models.Booking;
import com.simats.eventdecorationitems.models.BookingApprovalRequest;
import com.simats.eventdecorationitems.models.BookingApprovalResponse;
import com.simats.eventdecorationitems.models.PendingBookingsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingRequestsActivity extends AppCompatActivity implements BookingRequestAdapter.OnBookingActionListener {

    private static final String TAG = "BookingRequests";

    private RecyclerView recyclerView;
    private BookingRequestAdapter adapter;
    private List<Booking> bookingList;
    private ProgressBar progressBar;
    private LinearLayout emptyState;
    private TextView pendingCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_requests);

        // Initialize views
        recyclerView = findViewById(R.id.bookings_recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        emptyState = findViewById(R.id.empty_state);
        pendingCountText = findViewById(R.id.pending_count);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> onBackPressed());

        // Setup RecyclerView
        bookingList = new ArrayList<>();
        adapter = new BookingRequestAdapter(bookingList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load pending bookings
        loadPendingBookings();
    }

    private void loadPendingBookings() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyState.setVisibility(View.GONE);

        ApiService apiService = ApiClient.getApiService();
        Call<PendingBookingsResponse> call = apiService.getPendingBookings();

        call.enqueue(new Callback<PendingBookingsResponse>() {
            @Override
            public void onResponse(Call<PendingBookingsResponse> call, Response<PendingBookingsResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    PendingBookingsResponse data = response.body();

                    if (data.isSuccess() && data.getBookings() != null) {
                        bookingList.clear();
                        bookingList.addAll(data.getBookings());
                        adapter.notifyDataSetChanged();

                        updateUI();
                        Log.d(TAG, "Loaded " + bookingList.size() + " pending bookings");
                    } else {
                        showEmptyState();
                        Log.e(TAG, "Failed: " + data.getMessage());
                    }
                } else {
                    showEmptyState();
                    Toast.makeText(BookingRequestsActivity.this, 
                        "Error loading bookings", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PendingBookingsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showEmptyState();
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
                Toast.makeText(BookingRequestsActivity.this, 
                    "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        if (bookingList.isEmpty()) {
            showEmptyState();
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyState.setVisibility(View.GONE);
            pendingCountText.setText(bookingList.size() + " Pending");
        }
    }

    private void showEmptyState() {
        recyclerView.setVisibility(View.GONE);
        emptyState.setVisibility(View.VISIBLE);
        pendingCountText.setText("0 Pending");
    }

    @Override
    public void onAccept(Booking booking, int position) {
        processBooking(booking, position, "accept");
    }

    @Override
    public void onReject(Booking booking, int position) {
        processBooking(booking, position, "reject");
    }

    private void processBooking(Booking booking, int position, String action) {
        progressBar.setVisibility(View.VISIBLE);

        BookingApprovalRequest request = new BookingApprovalRequest(booking.getId(), action);
        ApiService apiService = ApiClient.getApiService();
        Call<BookingApprovalResponse> call = apiService.approveBooking(request);

        call.enqueue(new Callback<BookingApprovalResponse>() {
            @Override
            public void onResponse(Call<BookingApprovalResponse> call, Response<BookingApprovalResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    BookingApprovalResponse result = response.body();

                    if (result.isSuccess()) {
                        // Remove item from list
                        adapter.removeItem(position);
                        updateUI();

                        String message = action.equals("accept") 
                            ? "Booking accepted successfully" 
                            : "Booking rejected successfully";
                        Toast.makeText(BookingRequestsActivity.this, message, Toast.LENGTH_SHORT).show();
                        
                        Log.d(TAG, "Booking " + booking.getId() + " " + action + "ed");
                    } else {
                        Toast.makeText(BookingRequestsActivity.this, 
                            result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BookingRequestsActivity.this, 
                        "Error processing booking", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BookingApprovalResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "API call failed: " + t.getMessage(), t);
                Toast.makeText(BookingRequestsActivity.this, 
                    "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
