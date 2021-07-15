package com.example.android.noteitdown.audionote;

import android.Manifest;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.noteitdown.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class AudioNoteActivity extends AppCompatActivity {

  private static String fileName;
    View view;
    ImageButton btnRec;
    TextView txtRecStatus;
    Chronometer timeRec;
    GifImageView gifView;
    boolean isRecording;
    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/NoteItDown");
 //   public static String fileName = "recorded4.3gp";
    String file;
    private MediaRecorder mediaRecorder;
//    String file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRec =  findViewById(R.id.btnRec);
        txtRecStatus = findViewById(R.id.txtRecStatus);
        gifView =  findViewById(R.id.gifview);
        timeRec = findViewById(R.id.timeRec);

        isRecording =false;

        askRuntimePermission();

        SimpleDateFormat format = new SimpleDateFormat("yyyyyMMdd_HHmmss", Locale.getDefault());
        String date = format.format(new Date());
//        fileName = "/recording_" +date +".3gp";
//        file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileName;
//        file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileName;

        fileName = path +"/recording_" +date +".3gp";

//        if(path.exists()){
//            path.mkdirs();
//        }

        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRecording){
                    try {
                        startRecording();
                        gifView.setVisibility(View.VISIBLE);
                        timeRec.setBase(SystemClock.elapsedRealtime());
                        timeRec.start();
                        txtRecStatus.setText("Recording...");
                        btnRec.setImageResource(R.drawable.ic_baseline_stop_24);
                        isRecording = true;
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage()+"Couldn't Record", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(isRecording){
//                    mediaRecorder.stop();
//                    mediaRecorder.reset();

                    stopRecording();
                    gifView.setVisibility(View.GONE);
                    timeRec.setBase(SystemClock.elapsedRealtime());
                    timeRec.stop();
                    txtRecStatus.setText("");
                    btnRec.setImageResource(R.drawable.ic_baseline_mic_24);
                    isRecording =false;
                }
            }
        });



    }

    private void startRecording(){

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(fileName);
        record();

//        try {
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//
//        }catch (IOException e){
//            e.printStackTrace();
//            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT);
//        }


    }

    private void stopRecording() {
        stopAudio();
//        if(mediaRecorder != null){
//            mediaRecorder.stop();
//            mediaRecorder.release();
//            mediaRecorder = null;
//        }
    }


    private void askRuntimePermission() {
        Dexter.withContext(getApplicationContext()).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Toast.makeText(getApplicationContext(),"Granted!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }


    private void stopAudio() {
        mediaRecorder.stop();
        mediaRecorder.release();
        txtRecStatus.setText("Recording Stopped!");
    }

    private void record() {
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT);
        }
        txtRecStatus.setText("Audio Recording.....");

    }
}



