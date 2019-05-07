package com.example.saiapi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saiapi.fragments.api.model.DeviceList;
import com.example.saiapi.fragments.JwtFragment;
import com.example.saiapi.fragments.ui.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private JwtFragment jwtFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();

    }

    private void loadFragment() {
        if (jwtFragment == null) {
            jwtFragment = new JwtFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, jwtFragment).commit();
    }



}
