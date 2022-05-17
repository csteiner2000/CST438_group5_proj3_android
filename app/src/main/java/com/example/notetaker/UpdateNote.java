package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notetaker.noterecycler.NoteData;

import java.util.List;

public class UpdateNote extends AppCompatActivity {
    private EditText newNoteTitle, newNoteText;

    private Button updateBtn;
    private TextView cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        newNoteTitle = (EditText) findViewById(R.id.newTitle);
        newNoteText = (EditText) findViewById(R.id.newNote);


        updateBtn = (Button) findViewById(R.id.update);


        cancelBtn = (TextView) findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateNote.this, Home.class);
                startActivity(intent);
                UpdateNote.this.finish();
            }
        });
    }
}