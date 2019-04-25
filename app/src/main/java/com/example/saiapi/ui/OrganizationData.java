package com.example.saiapi.ui;

import android.util.Log;

import com.example.saiapi.api.model.OrganizationList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganizationData {
    private MainActivity mainActivity;
    private static final String TAG = "OrganizationList";

    public OrganizationData(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


}
