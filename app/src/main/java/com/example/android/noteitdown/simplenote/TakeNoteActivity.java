package com.example.android.noteitdown.simplenote;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.noteitdown.R;
import com.example.android.noteitdown.ui.home.HomeFragment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TakeNoteActivity extends AppCompatActivity {
    EditText titleView,descriptionView;
    Button saveBtn;
    LocalDate dateTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleView = findViewById(R.id.title);
        descriptionView = findViewById(R.id.description);
        saveBtn = findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(view -> {
            //doubt
            dateTime = LocalDate.now();
            Date date = Date.from(dateTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Note newNote = new Note();
            newNote.setTitle(titleView.getText().toString());
            newNote.setDescription((descriptionView.getText().toString()));
            newNote.setDateOfCreation(date.toString());

            AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
            db.notesdao().saveNote(newNote);

//            HomeFragment.allnotes.add(newNote);
//            HomeFragment.adapter.notifyDataSetChanged();

        });

    }
}