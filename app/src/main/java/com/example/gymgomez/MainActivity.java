package com.example.gymgomez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SessionManagerActivity sessionManager = new SessionManagerActivity(this);
        if (sessionManager.getToken() != null) {
            // El usuario ya está logueado, saltamos el login
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }


        // Comprobar si ya está logueado
        SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String savedUser = prefs.getString("username", null);

        if (savedUser != null) {
            // Ya logueado, ir a SplashActivity directamente
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            intent.putExtra("USERNAME", savedUser);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginRequestActivity loginRequest = new LoginRequestActivity(email, password);

                RetrofitClientActivity.getApiServiceActivity().login(loginRequest).enqueue(new retrofit2.Callback<LoginResponseActivity>() {
                    @Override
                    public void onResponse(Call<LoginResponseActivity> call, retrofit2.Response<LoginResponseActivity> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // Guardar el token
                            SessionManagerActivity sessionManager = new SessionManagerActivity(MainActivity.this);
                            sessionManager.saveToken(response.body().getToken());

                            Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        } else {
                            Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    }//posiblemente tengo que acomodar esto

                    @Override
                    public void onFailure(Call<LoginResponseActivity> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
