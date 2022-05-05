package com.example.notetaker.noterecycler;

public class NoteData {
    String noteTitle;
    String noteText;
    String date;

    public NoteData(String noteTitle, String date, String noteText) {
        this.noteTitle = noteTitle;
        this.date = date;
        this.noteText = noteText;
    }

    public NoteData(String noteText, String date) {
        this.noteText = noteText;
        this.date = date;
    }
}
