package com.example.android.noteitdown.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;
import com.example.android.noteitdown.simplenote.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    Context context;
    OnNoteClickListener listener;
    List<Note> notesList;

    public NoteAdapter(Context context, List<Note> notesList, OnNoteClickListener listener){
        this.context=context;
        this.listener=listener;
        this.notesList=notesList;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.note_item_layout, parent, false);
        return new NoteViewHolder(rowlayout);
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
    Note tempNote = notesList.get(position);
    holder.title.setText(tempNote.getTitle());
    holder.desc.setText(tempNote.getDescription());
        // holder.date.setText(tempNote.getDateOfCreation().toString().substring(0,10));

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onNoteItemClick(view,holder.getAbsoluteAdapterPosition());
        }
    });

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(context,"Long Clicked",Toast.LENGTH_LONG).show();
            listener.onNoteItemLongClick(view,holder.getAbsoluteAdapterPosition());
            return false;
        }
    });


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
