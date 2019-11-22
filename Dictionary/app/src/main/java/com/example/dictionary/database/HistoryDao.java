package com.example.dictionary.database;

import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dictionary.Object.History;

import java.util.List;
@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<History> history);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(History history);

    @Update
    public void update(History... history);

    @Delete
    public void delete(History history);

    //getting all history to place into recyclerView
    @Query("SELECT * FROM History")
    public List<History> getAllHistory();



}
