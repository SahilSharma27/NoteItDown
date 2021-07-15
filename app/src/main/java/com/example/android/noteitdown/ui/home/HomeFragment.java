package com.example.android.noteitdown.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.AppDatabase;
import com.example.android.noteitdown.R;
import com.example.android.noteitdown.reminder.OnRemClickListener;
import com.example.android.noteitdown.reminder.Reminder;
import com.example.android.noteitdown.reminder.ReminderActivity;
import com.example.android.noteitdown.reminder.ReminderAdapter;
import com.example.android.noteitdown.simplenote.Note;
import com.example.android.noteitdown.simplenote.TakeNoteActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    public  static List<Note> allnotes;
    public static ReminderAdapter adapter1;
    List <Reminder> allRems;
    public static NoteAdapter adapter;
    RecyclerView recyclerView,recyclerView1;

    AppDatabase db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = root.findViewById(R.id.notesRCV);
        recyclerView1= root.findViewById(R.id.remsRCV);


        allnotes = new ArrayList<>();
        allRems = new ArrayList<>();

        db = AppDatabase.getDBInstance(root.getContext().getApplicationContext());

        allnotes =  db.notesdao().getAllNotes();
        allRems = db.remsdao().getAllReminders();


        adapter = new NoteAdapter(getContext(), allnotes, new OnNoteClickListener() {
            @Override
            public void onNoteItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), TakeNoteActivity.class);
                intent.putExtra("Title", allnotes.get(position).getTitle());
                intent.putExtra("Description", allnotes.get(position).getDescription());
                startActivity(intent);

            }

            @Override
            public void onNoteItemLongClick(View view, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this Note?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(getContext(),"Long Clicked",Toast.LENGTH_LONG).show();
                                db.notesdao().deleteNote(allnotes.get(position));
                                allnotes.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

            }
        });

        adapter.notifyDataSetChanged();

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
//        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter1 = new ReminderAdapter(getContext(), allRems, new OnRemClickListener() {
            @Override
            public void onRemItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ReminderActivity.class);
                intent.putExtra("Title", allRems.get(position).getRemtitle());
                intent.putExtra("Description", allRems.get(position).getRemDescription());
                intent.putExtra("Date", allRems.get(position).getRemDate());
                intent.putExtra("Time", allRems.get(position).getRemTime());

                startActivity(intent);

            }

            @Override
            public void onRemItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this Note?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                Toast.makeText(getContext(),"Long Clicked",Toast.LENGTH_LONG).show();
                                db.remsdao().removeReminder(allRems.get(position));
                                allRems.remove(position);
                                adapter1.notifyDataSetChanged();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        adapter1.notifyDataSetChanged();
        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
//        linearLayoutManager1.setReverseLayout(true);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.setAdapter(adapter1);


        return root;
    }
}