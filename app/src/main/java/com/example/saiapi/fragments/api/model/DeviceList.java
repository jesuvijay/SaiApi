package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceList {

    @SerializedName("result")
    private List<Device> devices;
    @SerializedName("totalCount")
    private String totalCount;

    public List<Device> getDevices() {
        return devices;
    }

    public String getTotalCount() {
        return totalCount;
    }
}
