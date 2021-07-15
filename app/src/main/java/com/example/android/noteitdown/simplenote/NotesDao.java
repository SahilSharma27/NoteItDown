package com.example.android.noteitdown.simplenote;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("select * FROM Note")
    List<Note> getAllNotes();

    @Query(" select * FROM Note WHERE noteid Like :Nid ")
    Note getNoteByNid(int Nid);

    @Insert
    void  saveNote(Note note);

    @Delete
    void deleteNote(Note note);


}