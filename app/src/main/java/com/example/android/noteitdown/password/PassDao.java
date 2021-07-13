package com.example.android.noteitdown.password;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PassDao {

    @Insert
    void savePass(PassWord pass);

    @Delete
    void removePass(PassWord pass);

    @Query("select * from PassWord")
    List<PassWord> getAllPasses();

    @Query("Select * FROM PassWord where PassTitle  LIKE :title")
    PassWord getPass(String title);
}
