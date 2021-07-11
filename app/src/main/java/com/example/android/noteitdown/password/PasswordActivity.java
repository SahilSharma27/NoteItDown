package com.example.android.noteitdown.password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.noteitdown.R;

public class PasswordActivity extends AppCompatActivity implements SavePassDialog.PassDialogListener {
    Button addPass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addPass = findViewById(R.id.addPassBtn);

        addPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        SavePassDialog savePassDialog = new SavePassDialog();
        savePassDialog.show(getSupportFragmentManager(),"Pass Dialog");
    }

    @Override
    public void applyTexts(String title, String username, String password) {
        Toast.makeText(PasswordActivity.this,"Password Saved",Toast.LENGTH_LONG).show();
    }
}