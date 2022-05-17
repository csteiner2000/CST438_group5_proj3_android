package com.example.notetaker.room;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notetaker.database.models.Notes;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface NotesDAO {

    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    @Query("SELECT * FROM notes")
    List<Notes> getAll();

    @Query("UPDATE notes SET noteTitle = :noteTitle, noteText = :noteText WHERE noteId = :noteId")
    void update(int noteId, String noteTitle, String noteText);

    @Delete
    void delete(Notes notes);
}
