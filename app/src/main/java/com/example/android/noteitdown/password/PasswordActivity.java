package com.example.android.noteitdown.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.noteitdown.R;
import com.example.android.noteitdown.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class PasswordActivity extends AppCompatActivity implements SavePassDialog.PassDialogListener {
    Button addPass ;

    RecyclerView passRCV;
    PassAdapater adapter;
    List<PassWord> allPasses;
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addPass = findViewById(R.id.addPassBtn);

        passRCV = findViewById(R.id.passRCV);

        allPasses = new ArrayList<>();

        db = AppDatabase.getDBInstance(this.getApplicationContext());

        allPasses = db.passDao().getAllPasses();


        adapter = new PassAdapater(this, allPasses, new OnPassClickListener() {
            @Override
            public void onPassItemClick(View view, int position) {
                db.passDao().removePass(allPasses.get(position));
                allPasses.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        passRCV.setLayoutManager(linearLayoutManager);
        passRCV.setAdapter(adapter);



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
        PassWord newPass =  new PassWord();
        newPass.setTitle(title);
        newPass.setUsername(username);
        newPass.setPassword(password);

        AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());

        db.passDao().savePass(newPass);
        adapter.notifyDataSetChanged();
        Toast.makeText(PasswordActivity.this,"Password Saved",Toast.LENGTH_LONG).show();
    }



}