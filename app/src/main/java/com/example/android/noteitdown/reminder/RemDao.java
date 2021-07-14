package com.example.android.noteitdown.reminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RemDao {

    @Insert
    void saveReminder(Reminder rem);

    @Delete
    void removeReminder(Reminder rem);

    @Query("Select * from Reminder")
    List<Reminder> getAllReminders();

    @Query("Select * from Reminder where RemTitle Like :remtitle")
    Reminder getReminder(String remtitle);
}
