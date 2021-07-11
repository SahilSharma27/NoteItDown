package com.example.android.noteitdown.simplenote;

import java.util.Date;

public class Note {
    private String Title;
    private String Description;
    private Date dateOfCreation;


    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }
}
