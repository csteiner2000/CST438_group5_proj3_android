package com.example.notetaker.noterecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetaker.Home;
import com.example.notetaker.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<NoteData> notes = new ArrayList<>();
    Context context;
    Home.ClickListener listener;

    public NoteAdapter(List<NoteData> notes, Context context, Home.ClickListener listener) {
        this.notes = notes;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.note_card, parent, false);

        return new NoteViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder viewHolder, final int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.noteTitle.setText(notes.get(position).noteTitle);
        viewHolder.noteDate.setText(notes.get(position).date);
        viewHolder.noteDate.setText(notes.get(position).noteText.substring(0, 20));
        viewHolder.view.setOnClickListener(v -> listener.click(index));
    }

    public void setNotes(List<NoteData> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
