package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class JwtToken {
    @SerializedName("jwt")
    private String jswttoken;

    public String getJswttoken() {
        return jswttoken;
    }

    public void setJswttoken(String jswttoken) {
        this.jswttoken = jswttoken;
    }
}
