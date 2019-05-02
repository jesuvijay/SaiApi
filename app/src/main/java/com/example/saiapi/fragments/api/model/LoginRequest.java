package com.example.saiapi.fragments.api.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String userName;

    public LoginRequest(String userName, String password) {

        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
