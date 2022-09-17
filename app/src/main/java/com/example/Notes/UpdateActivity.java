package com.example.Notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Notes.database.Note;
import com.example.Notes.databinding.ActivityUpdateBinding;
import com.example.Notes.models.NotesViewModel;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;
    NotesViewModel viewModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        setContentView(view);
        int uid = 0;
        final String[] title = {""};
        final String[] body = {""};

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            uid = extras.getInt("uid");
            title[0] = extras.getString("title");
            body[0] = extras.getString("body");
            //The key argument here must match that used in the other activity
        }

        if (uid != 0 && !title[0].isEmpty() && !body[0].isEmpty()){
            binding.etTitleUpdate.setText(title[0]);
            binding.etBodyUpdate.setText(body[0]);
        }


        int finalUid = uid;
        binding.btnUpdate.setOnClickListener(view1 -> {
            title[0] = binding.etTitleUpdate.getText().toString();
            body[0] = binding.etBodyUpdate.getText().toString();

            if (title[0].isEmpty() || body[0].isEmpty()){
                Toast.makeText(this,"No field should be empty",Toast.LENGTH_SHORT).show();
            }else {
                viewModel.update(new Note(finalUid, title[0], body[0]));
                finish();
            }
        });

        binding.btnDelete.setOnClickListener(view1 -> {
            viewModel.delete(new Note(finalUid, title[0], body[0]));
            finish();
        });

    }

}