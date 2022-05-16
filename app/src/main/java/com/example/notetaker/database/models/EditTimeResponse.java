package com.example.notetaker.database.models;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class EditTimeResponse {
    @SerializedName("editTime")
    public Timestamp editTime;
}

