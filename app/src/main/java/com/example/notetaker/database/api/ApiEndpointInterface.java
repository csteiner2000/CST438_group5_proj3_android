package com.example.notetaker.database.api;

import com.example.notetaker.database.models.User;
import com.example.notetaker.noterecycler.NoteData;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndpointInterface {
    @GET("https://arcane-crag-19565.herokuapp.com/api/login")
    Call<User.ApiResponse> getUser(
            @Query("uName") String username,
            @Query("pWord") String password
    );

    @GET("https://arcane-crag-19565.herokuapp.com/api/note/all")
    Call<List<NoteData>> getAllNotes(@Query("userId") int userId);
}
