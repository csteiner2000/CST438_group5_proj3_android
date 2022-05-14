package com.example.notetaker.noterecycler;

import com.google.gson.annotations.SerializedName;

public class NoteData {
    @SerializedName("noteTitle")
    public String title;
    public String date;
    @SerializedName("noteText")
    public String text;
    @SerializedName("editTime")
    public String editTime;

    public NoteData(String title, String date, String text) {
        this.title = title;
        this.date = date;
        this.text = text;
    }

    public NoteData(String text, String date) {
        this.text = text;
        this.date = date;
    }
}
