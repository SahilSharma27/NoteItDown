package com.example.android.noteitdown.password;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;


import com.example.android.noteitdown.R;



public class SavePassDialog extends AppCompatDialogFragment {
    private EditText passTitle,passUserName,passPassword;
    private PassDialogListener listener;


    @NonNull

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view  = layoutInflater.inflate(R.layout.passdialog_layout,null);

        builder.setView(view)
                .setTitle("Add Password")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                }).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String pass_title = passTitle.getText().toString();
                String pass_username = passUserName.getText().toString();
                String password = passPassword.getText().toString();
                listener.applyTexts(pass_title,pass_username,password);

            }
        });
        passTitle = view.findViewById(R.id.pass_title);
        passUserName = view.findViewById(R.id.pass_user_name);
        passPassword = view.findViewById(R.id.pass_password);



        return  builder.create();


    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (PassDialogListener) context;
        }catch (ClassCastException e){
           throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }



    public interface  PassDialogListener{
        void applyTexts(String title,String username, String password);
    }

}
