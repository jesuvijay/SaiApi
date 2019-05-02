package com.example.saiapi.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationList {

    @SerializedName("totalCount")
    private String totalCount;

    @SerializedName("result")
    private List<ApplicationLora> applicationLoras;


}
