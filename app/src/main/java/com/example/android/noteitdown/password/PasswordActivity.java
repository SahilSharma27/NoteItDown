package com.example.android.noteitdown.password;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.AppDatabase;
import com.example.android.noteitdown.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class PasswordActivity extends AppCompatActivity implements SavePassDialog.PassDialogListener {
    Button addPass;

    RecyclerView passRCV;
    PassAdapater adapter;
    List<PassWord> allPasses;
    AppDatabase db;
    CheckBox checkBox;
    EditText titleET, usernameET, passET;

    int Clickedposition;


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

                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Please Verify")
                        .setDescription("User Authentication is required to process")
                        .setNegativeButtonText("Cancel")
                        .build();

                getPrompt().authenticate(promptInfo);
                Clickedposition = position;
//


//
//                db.passDao().removePass(allPasses.get(position));
//                allPasses.remove(position);
//                adapter.notifyDataSetChanged();
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
        Toast.makeText(PasswordActivity.this, "Password Saved", Toast.LENGTH_LONG).show();
    }

    private BiometricPrompt getPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }


            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("Authentication Successful");
                showPass();

                //open dialog show pass
            }


            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("Authentication Failed");
            }
        };
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;

    }

    private void notifyUser(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showPass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PasswordActivity.this);
        LayoutInflater layoutInflater = PasswordActivity.this.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.passdialog_layout, null);

        titleET = view.findViewById(R.id.pass_title);
        usernameET = view.findViewById(R.id.pass_user_name);
        passET = view.findViewById(R.id.pass_password);
        checkBox = view.findViewById(R.id.show_pass_check);


        builder.setView(view)
                .setTitle("Saved Password")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();


                    }
                }).setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.passDao().removePass(allPasses.get(Clickedposition));
                allPasses.remove(Clickedposition);
                adapter.notifyDataSetChanged();

            }
        });

        titleET.setText(allPasses.get(Clickedposition).getTitle());
        usernameET.setText(allPasses.get(Clickedposition).getUsername());
        passET.setText(allPasses.get(Clickedposition).getPassword());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    passET.setTransformationMethod(null);
                } else {
                    passET.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });


        builder.create();
        builder.show();


    }


}