package com.example.notetaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences shared = getSharedPreferences("noteTaker", MODE_PRIVATE);
        Intent intent;

        // bypass login screen if user has previously logged in
        if (shared.contains("userId")) {
            intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        } else {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        }
        startActivity(intent);
    }
}