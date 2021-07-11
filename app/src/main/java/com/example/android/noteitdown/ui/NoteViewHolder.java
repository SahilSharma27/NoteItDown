package com.example.android.noteitdown.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView title,desc,date;
    View itemView;


    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView =  itemView;
        this.title = itemView.findViewById(R.id.noteTitle);
        this.desc = itemView.findViewById(R.id.notedescription);
        this.date =  itemView.findViewById(R.id.notedate);
    }
}
