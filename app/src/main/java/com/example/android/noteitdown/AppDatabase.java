package com.example.android.noteitdown;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android.noteitdown.password.PassDao;
import com.example.android.noteitdown.password.PassWord;
import com.example.android.noteitdown.reminder.RemDao;
import com.example.android.noteitdown.reminder.Reminder;
import com.example.android.noteitdown.simplenote.Note;
import com.example.android.noteitdown.simplenote.NotesDao;

@Database(entities = {  Note.class,
                        PassWord.class,
                        Reminder.class
                    }, version  = 1,exportSchema = false )
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"NOTES_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract NotesDao notesdao();

    public abstract RemDao remsdao();

     public abstract PassDao passDao();
}
