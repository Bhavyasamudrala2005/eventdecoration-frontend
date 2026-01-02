package com.simats.eventdecorationitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.simats.eventdecorationitems.models.SignupRequest;
import com.simats.eventdecorationitems.models.SignupResponse;
import com.simats.eventdecorationitems.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.name_edit_text);
        etEmail = findViewById(R.id.email_edit_text);
        etPhone = findViewById(R.id.phone_edit_text);
        etPassword = findViewById(R.id.password_edit_text);
        etConfirmPassword = findViewById(R.id.confirm_password_edit_text);
        btnRegister = findViewById(R.id.register_button);
        tvLogin = findViewById(R.id.login_text_view);

        btnRegister.setOnClickListener(v -> signupUser());

        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    // ✅ FULL SIGNUP METHOD (Retrofit + JSON)
    private void signupUser() {

        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        SignupRequest request = new SignupRequest(
                name,
                email,
                phone,
                password,
                confirmPassword
        );

        ApiClient.getApiService().signupUser(request)
                .enqueue(new Callback<SignupResponse>() {

                    @Override
                    public void onResponse(Call<SignupResponse> call,
                                           Response<SignupResponse> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            Toast.makeText(SignupActivity.this,
                                    response.body().getMessage(),
                                    Toast.LENGTH_LONG).show();

                            if ("success".equalsIgnoreCase(response.body().getStatus())) {
                                startActivity(new Intent(
                                        SignupActivity.this,
                                        LoginActivity.class
                                ));
                                finish();
                            }

                        } else {
                            Toast.makeText(SignupActivity.this,
                                    "Signup failed. Try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupResponse> call, Throwable t) {
                        Toast.makeText(SignupActivity.this,
                                "Error: " + t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
