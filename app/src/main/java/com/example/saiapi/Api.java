package com.example.saiapi;

import android.util.Log;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "http://52.14.32.86:8080/";

    @Headers({"Content-Type: application/json"})
    @POST("/api/internal/login")
    Call<Login> getJwt(@Body LoginRequest loginRequest);
}
