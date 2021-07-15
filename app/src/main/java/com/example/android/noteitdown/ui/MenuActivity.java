package com.example.android.noteitdown.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.android.noteitdown.R;
import com.example.android.noteitdown.audionote.AudioNoteActivity;
import com.example.android.noteitdown.canvas.CanvasActivity;
import com.example.android.noteitdown.password.PasswordActivity;
import com.example.android.noteitdown.reminder.ReminderActivity;
import com.example.android.noteitdown.simplenote.TakeNoteActivity;
import com.example.android.noteitdown.speechtotextnote.SpeechToTextActivity;

public class MenuActivity extends AppCompatActivity {
    CardView NoteCard,CLCard,RemCard,BoardCard,PassCard,ScanCard,SpeechCard,AudioCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        NoteCard= findViewById(R.id.notecard);
        CLCard = findViewById(R.id.clcard);
        RemCard = findViewById(R.id.remcard);
        BoardCard = findViewById(R.id.boardcard);
        PassCard =  findViewById(R.id.passcard);
        ScanCard =  findViewById(R.id.scancard);
        SpeechCard = findViewById(R.id.speechcard);
        AudioCard = findViewById(R.id.audiocard);

        NoteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity(TakeNoteActivity.class);
            }
        });

        CLCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity(CheckListActivity.class);
            }
        });

        RemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity(ReminderActivity.class);
            }
        });

        PassCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity(PasswordActivity.class);
            }
        });

        SpeechCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity(SpeechToTextActivity.class);
            }
        });

        AudioCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity((AudioNoteActivity.class));
            }
        });

        BoardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity(CanvasActivity.class);
            }
        });
    }

    public void startNextActivity(Class activity){

        Intent intent =  new Intent(MenuActivity.this,activity);
        startActivity(intent);

    }
}