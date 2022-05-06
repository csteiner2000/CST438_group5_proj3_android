package com.example.notetaker.noterecycler;

public class NoteData {
    String title;
    String date;
    String text;

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
