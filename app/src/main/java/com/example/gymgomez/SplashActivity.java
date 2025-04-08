package com.example.gymgomez;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String username = getIntent().getStringExtra("USERNAME");

        TextView tvSplash = findViewById(R.id.tvSplash);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        tvSplash.setText("Cargando datos para " + username + "...");

        Animation fade = new AlphaAnimation(0.0f, 1.0f);
        fade.setDuration(500);
        fade.setRepeatMode(Animation.REVERSE);
        fade.setRepeatCount(Animation.INFINITE);
        tvSplash.startAnimation(fade);

        progressBar.setVisibility(ProgressBar.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, InfoActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}


