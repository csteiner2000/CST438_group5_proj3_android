package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.notetaker.noterecycler.NoteAdapter;
import com.example.notetaker.noterecycler.NoteData;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    ClickListener listener;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<NoteData> list = getData();

        listener = new ClickListener();
        adapter = new NoteAdapter(list, getApplicationContext(), listener);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
    }

    // Sample data for RecyclerView
    private List<NoteData> getData() {
        List<NoteData> list = new ArrayList<>();
        list.add(new NoteData("First Exam", "May 23, 2015"));
        list.add(new NoteData("Second Exam", "June 09, 2015"));
        list.add(new NoteData("My Test Exam", "April 27, 2017"));

        return list;
    }

    public class ClickListener {
        public void click(int noteID) {
            Intent intent = new Intent(getApplicationContext(), UpdateNote.class);
            intent.putExtra("noteID", noteID);
            startActivity(intent);
        }
    }
}
