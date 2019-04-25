package com.example.saiapi.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrganizationList {
    @SerializedName("totalCount")
    private String totalCount;
    @SerializedName("result")
    private List<Organization> organizations;

    public String getTotalCount() {
        return totalCount;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }
}
