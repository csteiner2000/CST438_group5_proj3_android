package com.example.notetaker.database.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    private Integer userId;
    private String username;
    private String password;

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public class ApiResponse {
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
}
