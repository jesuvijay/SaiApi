package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationList {

    @SerializedName("totalCount")
    private String totalCount;

    @SerializedName("result")
    private List<ApplicationLora> applicationLoras;

    public String getTotalCount() {
        return totalCount;
    }

    public List<ApplicationLora> getApplicationLoras() {
        return applicationLoras;
    }
}
