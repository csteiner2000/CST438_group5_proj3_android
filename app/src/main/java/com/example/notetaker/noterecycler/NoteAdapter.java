package com.example.notetaker.noterecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notetaker.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private List<NoteData> notes = new ArrayList<>();
    Context context;
    View.OnClickListener listener;

    public NoteAdapter(List<NoteData> notes, Context context, View.OnClickListener listener) {
        this.notes = notes;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View photoView = inflater.inflate(R.layout.note_card, parent, false);

        NoteViewHolder viewHolder = new NoteViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder viewHolder, final int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.examName.setText(notes.get(position).note);
        viewHolder.examDate.setText(notes.get(position).date);
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
