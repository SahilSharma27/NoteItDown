package com.example.android.noteitdown.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;
import com.example.android.noteitdown.simplenote.AppDatabase;
import com.example.android.noteitdown.simplenote.Note;
import com.example.android.noteitdown.ui.NoteAdapter;
import com.example.android.noteitdown.ui.OnNoteClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {
    public  static List<Note> allnotes;
    RecyclerView recyclerView;
    public static NoteAdapter adapter;
    AppDatabase db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        allnotes = new ArrayList<>();

        db = AppDatabase.getDBInstance(root.getContext().getApplicationContext());
        allnotes =  db.notesdao().getAllNotes();


        recyclerView = root.findViewById(R.id.notesRCV);
        adapter = new NoteAdapter(getContext(), allnotes, new OnNoteClickListener() {
            @Override
            public void onNoteItemClick(View view, int position) {
                db.notesdao().deleteNote(allnotes.get(position));
                allnotes.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return root;
    }
}