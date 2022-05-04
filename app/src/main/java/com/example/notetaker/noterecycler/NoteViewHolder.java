// ViewHolder code for RecyclerView
package com.example.notetaker.noterecycler;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notetaker.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    TextView noteTitle;
    TextView noteDate;
    TextView noteTextShort;
    View view;

    NoteViewHolder(View itemView) {
        super(itemView);
        noteTitle = (TextView)itemView.findViewById(R.id.noteTitle);
        noteDate = (TextView)itemView.findViewById(R.id.noteDate);
        noteTextShort = (TextView)itemView.findViewById(R.id.noteTextShort);
        view  = itemView;
    }
}