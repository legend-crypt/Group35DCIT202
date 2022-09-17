package com.example.Notes.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.Notes.database.Note;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private final NotesRepository notesRepo;
    private final LiveData<List<Note>> allNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepo = new NotesRepository(application);
        allNotes = notesRepo.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public Note getNote(int uid){
        return notesRepo.getNoteById(uid);
    }

    public void insert(Note note) {
        notesRepo.insert(note);
    }
    public void update(Note note) {
        notesRepo.update(note);
    }

    public void delete(Note note) {
        notesRepo.deleteNote(note);
    }
}
