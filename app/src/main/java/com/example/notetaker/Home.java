package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.notetaker.database.RetrofitClientInstance;
import com.example.notetaker.database.api.ApiEndpointInterface;
import com.example.notetaker.noterecycler.NoteAdapter;
import com.example.notetaker.noterecycler.NoteData;

import java.util.ArrayList;
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


        ApiEndpointInterface api = RetrofitClientInstance.getRetrofitInstance().create(ApiEndpointInterface.class);
        Call<List<NoteData>> call1 = api.getAllNotes(1);
        call1.enqueue(new Callback<List<NoteData>>() {
            @Override
            public void onResponse(Call<List<NoteData>> call, Response<List<NoteData>> response) {
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
            public void onFailure(Call<List<NoteData>> call, Throwable t) {
                Log.d("Data_response", "onFailure: " + call);
                Log.d("Data_response", "onFailure: failed to get an api response");
            }
        });
    }

    public class ClickListener {
        public void click(int noteIndex) {
            Intent intent = new Intent(getApplicationContext(), UpdateNote.class);
            intent.putExtra("noteIndex", noteIndex);
            startActivity(intent);
        }
    }
}

