package com.example.android.noteitdown.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.android.noteitdown.R;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {

    EditText titleET,desET,dateET,timeET;
    Button saveRemBtn;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleET = findViewById(R.id.remtitle);
        desET =findViewById(R.id.remdescription);
        dateET =findViewById(R.id.remdate);
        timeET = findViewById(R.id.remtime);

        saveRemBtn = findViewById(R.id.remsave_btn);


         final Calendar calendar = Calendar.getInstance();
         int year = calendar.get(calendar.YEAR);
         int month = calendar.get(calendar.MONDAY);
         int day = calendar.get(calendar.DAY_OF_MONTH);


        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(ReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String date = dayOfMonth + "/" + month + "/" + year;
                                dateET.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });


        timeET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar1 = Calendar.getInstance();
                int hour  = calendar1.get(Calendar.HOUR_OF_DAY);
                int minute = calendar1.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(ReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker datePicker, int hourOfDay, int minute) {

                                String time = hourOfDay + ":" + minute;
                                timeET.setText(time);
                            }
                        },hour,minute,false);
                timePickerDialog.show();
            }
        });


    }
}