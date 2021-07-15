package com.example.android.noteitdown.reminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.noteitdown.AppDatabase;
import com.example.android.noteitdown.R;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {

    EditText titleET, desET, dateET, timeET;
    Button saveRemBtn;
    //    DatePickerDialog.OnDateSetListener setListener;
    Calendar calendar1;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createNotificationChannel();
        Intent intent = getIntent();


        titleET = findViewById(R.id.remtitle);
        desET = findViewById(R.id.remdescription);
        dateET = findViewById(R.id.remdate);
        timeET = findViewById(R.id.remtime);

        titleET.setText(intent.getStringExtra("Title"));
        desET.setText(intent.getStringExtra("Description"));
        dateET.setText(intent.getStringExtra("Date"));
        timeET.setText(intent.getStringExtra("Time"));

        saveRemBtn = findViewById(R.id.remsave_btn);


        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(ReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                month = month + 1;
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

                calendar1 = Calendar.getInstance();
                int hour  = calendar1.get(Calendar.HOUR_OF_DAY);
                int minute = calendar1.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(ReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                cal = Calendar.getInstance();
                                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                cal.set(Calendar.MINUTE, minute);
                                cal.set(Calendar.SECOND, 0);

                                String time = hourOfDay + ":" + minute;
                                timeET.setText(time);
                            }
                        },hour,minute,false);
                timePickerDialog.show();
            }
        });


        saveRemBtn.setOnClickListener(view -> {

            Reminder newrem = new Reminder();
            newrem.setRemtitle(titleET.getText().toString());
            newrem.setRemDescription(desET.getText().toString());
            newrem.setRemTime(timeET.getText().toString());
            newrem.setRemDate(dateET.getText().toString());
            newrem.setRemPriority(1);

            AppDatabase db = AppDatabase.getDBInstance(this.getApplicationContext());
            db.remsdao().saveReminder(newrem);

            Toast.makeText(this, "Reminder Set!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


//            Intent intent = new Intent(this,MenuActivity.class);
//            startActivity(intent);

        });


    }


    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "NoteItDownChannel";
            String description = "Channel for NoteItDown";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("noteitdown", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }


    }
}