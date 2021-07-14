package com.example.android.noteitdown.reminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    public int remId;

    @ColumnInfo(name = "RemTitle")
    private String remtitle;

    @ColumnInfo(name = "RemDescription")
    private String remDescription;

    @ColumnInfo(name = "RemDate")
    private String remDate;

    @ColumnInfo(name = "RemTime")
    private String remTime;

    @ColumnInfo(name = "RemPriority")
    private int remPriority;//0,1,2

    public String getRemtitle() {
        return remtitle;
    }

    public void setRemtitle(String remtitle) {
        this.remtitle = remtitle;
    }

    public String getRemDescription() {
        return remDescription;
    }

    public void setRemDescription(String remDescription) {
        this.remDescription = remDescription;
    }

    public String getRemDate() {
        return remDate;
    }

    public void setRemDate(String remDate) {
        this.remDate = remDate;
    }

    public String getRemTime() {
        return remTime;
    }

    public void setRemTime(String remTime) {
        this.remTime = remTime;
    }

    public int getRemPriority() {
        return remPriority;
    }

    public void setRemPriority(int remPriority) {
        this.remPriority = remPriority;
    }
}

