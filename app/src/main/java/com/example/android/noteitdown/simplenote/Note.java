package com.example.android.noteitdown.simplenote;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int noteid;

    @ColumnInfo(name = "NoteTitle")
    private String Title;

    @ColumnInfo(name = "NoteDescription")
    private String Description;

    @ColumnInfo(name = "NoteDOC")
    private String dateOfCreation;


    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
