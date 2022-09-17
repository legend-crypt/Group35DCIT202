package com.example.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.Notes.database.Note;
import com.example.Notes.databinding.ActivityMainBinding;
import com.example.Notes.models.NotesViewModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    ActivityMainBinding binding;
    List<Note> notes = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NotesViewModel viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        setContentView(view);




        final NotesListAdapter adapter = new NotesListAdapter(new NotesListAdapter.NoteDiff(), this);

        binding.rvNotes.setAdapter(adapter);
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(this));


        viewModel.getAllNotes().observe(this, adapter::submitList);

        binding.btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        viewModel.getAllNotes().observe(this, notes -> {
            this.notes = notes;
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

        Log.d("list",notes.get(position).getTitle());

        intent.putExtra("uid", notes.get(position).getUid());
        intent.putExtra("title", notes.get(position).getTitle());
        intent.putExtra("body", notes.get(position).getBody());


        startActivity(intent);
    }
}