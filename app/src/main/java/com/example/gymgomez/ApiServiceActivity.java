package com.example.gymgomez;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServiceActivity {
    @POST("login")
    Call<LoginResponseActivity> login(@Body LoginRequestActivity loginRequest);
}