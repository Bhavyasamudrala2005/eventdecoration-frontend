package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.simats.eventdecorationitems.models.AdminLoginRequest;
import com.simats.eventdecorationitems.models.AdminLoginResponse;
import com.simats.eventdecorationitems.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        etUsername = findViewById(R.id.username_edit_text);
        etPassword = findViewById(R.id.password_edit_text);
        btnLogin = findViewById(R.id.login_button);
        tvUserLogin = findViewById(R.id.user_login_text_view);

        tvUserLogin.setOnClickListener(v -> onBackPressed());
        btnLogin.setOnClickListener(v -> adminLogin());
    }

    private void adminLogin() {

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminLoginRequest request =
                new AdminLoginRequest(username, password);

        ApiClient.getApiService()
                .adminLogin(request)
                .enqueue(new Callback<AdminLoginResponse>() {

                    @Override
                    public void onResponse(Call<AdminLoginResponse> call,
                                           Response<AdminLoginResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            if ("success".equalsIgnoreCase(response.body().getStatus())) {

                                Toast.makeText(AdminLoginActivity.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(
                                        AdminLoginActivity.this,
                                        AdminDashboardActivity.class
                                ));
                                finish();

                            } else {
                                Toast.makeText(AdminLoginActivity.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(AdminLoginActivity.this,
                                    "Admin login failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AdminLoginResponse> call, Throwable t) {
                        Toast.makeText(AdminLoginActivity.this,
                                "Error: " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
