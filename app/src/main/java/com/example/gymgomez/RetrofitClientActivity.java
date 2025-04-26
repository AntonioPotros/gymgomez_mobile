package com.example.gymgomez;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Retrofit;

public class RetrofitClientActivity {
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";
    private static Retrofit retrofit = null;

    public static ApiServiceActivity getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiServiceActivity.class);
    }

    public static ApiServiceActivity getApiServiceActivity() {
        return null;
    }
}
