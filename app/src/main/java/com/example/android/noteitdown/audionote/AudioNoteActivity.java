package com.example.android.noteitdown.audionote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.noteitdown.R;

import java.io.File;
import java.io.IOException;

public class AudioNoteActivity extends AppCompatActivity {
    MediaRecorder mediaRecorder;
    TextView textView ;

    public static String fileName = "recorded.3gp";

    String file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        textView = findViewById(R.id.textview);
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(file);
    }

    public void onClick(View v){
        if(v.getId() == R.id.buttonRecord){
            //Record
            record();
        }
        else if(v.getId() == R.id.buttonStop){
            //Stop
            stopAudio();
        }else if(v.getId() == R.id.buttonPlay){
            //Play
            play();
        }
    }

    private void play() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(file);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView.setText("Playing Recorded Audio ...");
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                textView.setText("Audio finished!");
                mediaPlayer.release();
            }
        });
    }

    private void stopAudio() {
        mediaRecorder.stop();
        mediaRecorder.release();
        textView.setText("Recording Stopped!");
    }

    private void record() {
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT);
        }
        textView.setText("Audio Recording.....");

    }



}