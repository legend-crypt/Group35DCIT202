package com.example.Notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.Notes.database.Note;
import com.example.Notes.databinding.ActivityAddBinding;
import com.example.Notes.models.NotesViewModel;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        NotesViewModel viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        setContentView(view);

        binding.btnSave.setOnClickListener(view1 -> {
            String title = binding.etTitle.getText().toString();
            String body = binding.etBody.getText().toString();

            if (title.isEmpty() || body.isEmpty()){
                Toast.makeText(this,"No field should be empty",Toast.LENGTH_SHORT).show();
            }else {
                viewModel.insert(new Note(0,title,body));
                finish();
            }

        });
    }
}