package com.example.Notes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Notes.database.Note;
import com.example.Notes.databinding.NoteItemBinding;

import java.util.List;

public class NotesListAdapter extends ListAdapter<Note, NotesListAdapter.ViewHolder> {


    private final RecyclerViewInterface recyclerViewInterface;

    public NotesListAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback, RecyclerViewInterface recyclerViewInterface) {
        super(diffCallback);
        this.recyclerViewInterface = recyclerViewInterface;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NoteItemBinding binding;

        public ViewHolder(NoteItemBinding binding, RecyclerViewInterface recyclerViewInterface) {
            super(binding.getRoot());
            this.binding = binding;
            binding.cdMainContainer.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });
        }

        public void bind(Note note) {
            binding.tvTitle.setText(note.getTitle());
            binding.tvBody.setText(note.getBody());

        }


    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteItemBinding binding = NoteItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.bind(note);
    }



    static class NoteDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getBody().equals(newItem.getBody()) && oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
