package com.example.dictionary.Object;

import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM History")
    public List<History> getAllHistory();

//    @Query ("SELECT * FROM History limit 1")
//    public History selectDelete();



//    @Query("SELECT COUNT(word) FROM History WHERE word = :word")
//    public History getHistory (String word);

//    @Query("SELECT count(1) FROM History WHERE id = :id")
//    public int getHistoryId(String id);

}
