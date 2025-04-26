package com.example.gymgomez;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginRequestActivity {
    private String email;
    private String password;

    public LoginRequestActivity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
