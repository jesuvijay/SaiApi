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



    // getDevice list
    private void getDeviceList(String jwtToken, String limit, String offset, String appId, String serviceProfId) {
        Log.d(TAG, "getApplicationList: " + jwtToken);
        final Call<DeviceList> applDeviceListCall = ApiClient.getApi().getDeviceList(jwtToken, limit, offset, appId, serviceProfId);
        applDeviceListCall.enqueue(new Callback<DeviceList>() {
            @Override
            public void onResponse(Call<DeviceList> call, Response<DeviceList> response) {
                Log.d(TAG, "onResponse: " + response.code());
                DeviceList deviceList = response.body();
                if (deviceList != null) {
                    Log.i(TAG, "onResponse: " + deviceList.toString());
                }
            }

            @Override
            public void onFailure(Call<DeviceList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                call.cancel();

            }
        });

    }


}
