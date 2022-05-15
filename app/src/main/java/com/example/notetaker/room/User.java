package com.example.notetaker.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "User", indices = {@Index(value = {"username"}, unique = true)})
public class User {
    @PrimaryKey
    @NonNull
    private Integer userId;
    private String username;
    private String password;

    public User(@NonNull Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    @NonNull
    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(@NonNull Integer userId) {
        this.userId = userId;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
