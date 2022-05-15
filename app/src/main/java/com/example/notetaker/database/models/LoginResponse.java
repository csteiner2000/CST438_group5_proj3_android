package com.example.notetaker.database.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("success")
    public boolean success;

    @SerializedName("error")
    public String error;

    @SerializedName("userId")
    public Integer userId;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;
}

