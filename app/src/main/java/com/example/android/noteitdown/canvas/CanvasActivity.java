package com.example.android.noteitdown.canvas;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.android.noteitdown.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CanvasActivity extends AppCompatActivity {
    private static String fileName;
    int defaultColour;
    SignatureView signatureView;
    ImageButton imgSave, imgErase, imgColor;
    SeekBar seekBar;
    TextView txtPenSize;
    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/NoteItDownCanvas");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        signatureView = findViewById(R.id.paintview);
        seekBar = findViewById(R.id.pensize);
        txtPenSize = findViewById(R.id.txtPenSize);
        imgErase = findViewById(R.id.btnEraser);
        imgSave = findViewById(R.id.btnSave);
        askPermission();

        SimpleDateFormat format = new SimpleDateFormat("yyyyyMMdd_HHmmss", Locale.getDefault());
        String date = format.format(new Date());

        fileName = path + "/" + date + ".png";

        if (!path.exists()) {
            path.mkdirs();
        }

        defaultColour = ContextCompat.getColor(CanvasActivity.this, R.color.black);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPenSize.setText(i + "dp");
                signatureView.setPenSize(i);
                seekBar.setMax(50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        imgColor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openColorPicker();
//            }
//        });

        imgErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signatureView.clearCanvas();
            }
        });

        imgSave.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveImage();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(CanvasActivity.this, "couldn't save", Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }


    private void saveImage() throws IOException {

        File file = new File(fileName);

        Bitmap bitmap = signatureView.getSignatureBitmap();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);

        byte[] bitmapData = bos.toByteArray();


        FileOutputStream fos = new FileOutputStream(file);

        fos.write(bitmapData);
        fos.flush();
        fos.close();
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
    }


    private void askPermission() {
        Dexter.withContext(getApplicationContext()).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Toast.makeText(getApplicationContext(), "Granted!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }
}