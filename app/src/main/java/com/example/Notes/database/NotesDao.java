package com.example.Notes.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void insert(Note note);

    @Query("SELECT * FROM notes_table")
    LiveData<List<Note>> getAllNotes();

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes_table WHERE uid LIKE :uid")
    Note getNoteByUid(int uid);

    @Update
    void update(Note note);
}
