package com.example.notetaker.database.api;

import com.example.notetaker.database.models.EditTimeResponse;
import com.example.notetaker.database.models.LoginResponse;
import com.example.notetaker.noterecycler.NoteData;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndpointInterface {
    @GET("https://arcane-crag-19565.herokuapp.com/api/login")
    Call<LoginResponse> getUser(
            @Query("uName") String username,
            @Query("pWord") String password
    );

    @GET("https://arcane-crag-19565.herokuapp.com/api/note/all")
    Call<List<NoteData>> getAllNotes(@Query("userId") int userId);

    @GET("https://arcane-crag-19565.herokuapp.com/api/note/timestamp")
    Call<EditTimeResponse> getEditTime(@Query("noteId") int noteId);

    @GET("https://arcane-crag-19565.herokuapp.com/api/note/update")
    Call<Void> updateNote(
            @Field("noteId") int noteId,
            @Field("title") String title,
            @Field("text") String text
    );

}
