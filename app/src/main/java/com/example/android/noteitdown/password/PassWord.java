package com.example.android.noteitdown.password;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PassWord {

    @PrimaryKey(autoGenerate = true)
    int PassId;

    @ColumnInfo(name = "PassTitle")
    private String title;

    @ColumnInfo(name = "PassUserName")
    private String Username;

    @ColumnInfo(name = "Password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
