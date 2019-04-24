package com.example.saiapi;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("jwt")
    private String jswttoken;

    public String getJswttoken() {
        return jswttoken;
    }

    public void setJswttoken(String jswttoken) {
        this.jswttoken = jswttoken;
    }

    public Login(String jswttoken) {
        this.jswttoken = jswttoken;
    }
}
