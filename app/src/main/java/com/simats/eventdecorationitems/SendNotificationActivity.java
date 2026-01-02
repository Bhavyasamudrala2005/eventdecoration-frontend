package com.simats.eventdecorationitems;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.simats.eventdecorationitems.models.SendNotificationRequest;
import com.simats.eventdecorationitems.models.SendNotificationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendNotificationActivity extends AppCompatActivity {

    private RadioGroup notificationTypeGroup;
    private RadioGroup recipientsGroup;
    private EditText titleEditText;
    private EditText messageEditText;
    private Button sendButton;
    private TextView charCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        // Initialize views
        ImageView backButton = findViewById(R.id.back_button);
        notificationTypeGroup = findViewById(R.id.notification_type_group);
        recipientsGroup = findViewById(R.id.recipients_group);
        titleEditText = findViewById(R.id.notification_title_edit_text);
        messageEditText = findViewById(R.id.notification_message_edit_text);
        sendButton = findViewById(R.id.send_notification_button);

        backButton.setOnClickListener(v -> onBackPressed());

        // Character count for message
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Update character count if view exists
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Send button click
        sendButton.setOnClickListener(v -> sendNotification());
    }

    private void sendNotification() {
        String title = titleEditText.getText().toString().trim();
        String message = messageEditText.getText().toString().trim();

        // Validate inputs
        if (title.isEmpty()) {
            titleEditText.setError("Title is required");
            titleEditText.requestFocus();
            return;
        }

        if (message.isEmpty()) {
            messageEditText.setError("Message is required");
            messageEditText.requestFocus();
            return;
        }

        // Get notification type
        String notificationType = getSelectedNotificationType();

        // Combine title and message
        String fullMessage = title + ": " + message;

        // Create request
        SendNotificationRequest request = new SendNotificationRequest(notificationType, fullMessage);

        // Disable button during request
        sendButton.setEnabled(false);
        sendButton.setText("Sending...");

        // Make API call
        ApiService apiService = ApiClient.getApiService();
        Call<SendNotificationResponse> call = apiService.sendNotification(request);

        call.enqueue(new Callback<SendNotificationResponse>() {
            @Override
            public void onResponse(Call<SendNotificationResponse> call, Response<SendNotificationResponse> response) {
                sendButton.setEnabled(true);
                sendButton.setText("Send Notification");

                if (response.isSuccessful() && response.body() != null) {
                    SendNotificationResponse data = response.body();
                    
                    if (data.isSuccess()) {
                        Toast.makeText(SendNotificationActivity.this, 
                            "Notification sent successfully!", Toast.LENGTH_SHORT).show();
                        
                        // Clear fields
                        titleEditText.setText("");
                        messageEditText.setText("");
                    } else {
                        Toast.makeText(SendNotificationActivity.this, 
                            "Failed: " + data.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SendNotificationActivity.this, 
                        "Error sending notification", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendNotificationResponse> call, Throwable t) {
                sendButton.setEnabled(true);
                sendButton.setText("Send Notification");
                Toast.makeText(SendNotificationActivity.this, 
                    "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getSelectedNotificationType() {
        int selectedId = notificationTypeGroup.getCheckedRadioButtonId();
        
        if (selectedId == R.id.radio_status_alert) {
            return "status_alert";
        } else if (selectedId == R.id.radio_reminder) {
            return "reminder";
        } else if (selectedId == R.id.radio_promotion) {
            return "promotion";
        }
        
        return "admin"; // Default type
    }
}
