package com.example.Notes.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String title;

    private String body;


    public Note(int uid, String title, String body) {
        this.uid = uid;
        this.title = title;
        this.body = body;
    }

    public int getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}
