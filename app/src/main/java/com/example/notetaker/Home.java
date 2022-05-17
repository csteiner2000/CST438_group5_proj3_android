package com.example.notetaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetaker.database.RetrofitClientInstance;
import com.example.notetaker.database.api.ApiEndpointInterface;
import com.example.notetaker.noterecycler.NoteAdapter;
import com.example.notetaker.noterecycler.NoteData;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    ClickListener listener;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get which user is logged in right now from shared preferences
        // TODO: add a way to log out
        SharedPreferences shared = getSharedPreferences("noteTaker", MODE_PRIVATE);
        int userId = shared.getInt("userId", -1);
        Log.d("SharedPreferences", "userId stored in SharedPreferences: " + userId);

        if (userId == -1) {
            Log.d("SharedPreferences", "Could not get userId value from shared preferences");
            Intent intent = new Intent(Home.this, LoginActivity.class);
            intent.putExtra("fatalError", "You need to be logged in to view your notes");
            startActivity(intent);
        }

        ApiEndpointInterface api = RetrofitClientInstance.getRetrofitInstance().create(ApiEndpointInterface.class);
        Call<List<NoteData>> call1 = api.getAllNotes(userId);
        call1.enqueue(new Callback<List<NoteData>>() {
            @Override
            public void onResponse(@NonNull Call<List<NoteData>> call, @NonNull Response<List<NoteData>> response) {
                List<NoteData> list = response.body();
                if (list == null) {
                    Log.d("Data_response", "onFailure: " + call);
                    Log.d("Data_response", "onFailure: could not parse response as list of notes");
                    return;
                }
                Log.d("Data_response", "onResponse: successfully got " + list.size() + " notes");
                listener = new ClickListener();
                adapter = new NoteAdapter(list, getApplicationContext(), listener);
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
            }

            @Override
            public void onFailure(@NonNull Call<List<NoteData>> call, @NonNull Throwable t) {
                Log.d("Data_response", "onFailure: " + call);
                Log.d("Data_response", "onFailure: failed to get an api response");
            }
        });
    }

    public class ClickListener {
        public void click(int noteIndex, List<NoteData> notes) {
            Intent intent = new Intent(getApplicationContext(), UpdateNote.class);
            intent.putExtra("noteIndex", noteIndex);
            startActivity(intent);
        }
    }
}

