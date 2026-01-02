package com.simats.eventdecorationitems;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // ================================================================
    // CHOOSE YOUR BASE URL:
    // ================================================================
    // FOR ANDROID EMULATOR: Use 10.0.2.2 (this maps to your PC's localhost)
    // FOR PHYSICAL DEVICE: Use your computer's IP address
    // ================================================================
    
    // Using your computer's IP address for physical device
    // IMPORTANT: Your phone must be on the SAME WiFi as your computer!
    public static final String BASE_URL = "http://10.50.30.120/eventease/";
    
    // ALTERNATIVE: For Emulator use this instead:
    // public static final String BASE_URL = "http://10.0.2.2/eventease/";

    private static Retrofit retrofit = null;
    private static ApiService apiService = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // Logging interceptor for debugging
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // OkHttpClient with proper timeouts
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = getClient().create(ApiService.class);
        }
        return apiService;
    }

    // Call this to reset the client if needed
    public static void resetClient() {
        retrofit = null;
        apiService = null;
    }
}
