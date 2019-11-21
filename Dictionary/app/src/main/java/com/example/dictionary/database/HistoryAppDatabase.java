package com.example.dictionary.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.dictionary.Object.History;
import com.example.dictionary.Object.HistoryDao;

@Database(entities = {History.class}, version = 1, exportSchema = false)
public abstract class HistoryAppDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();

    private static HistoryAppDatabase instance;
    public static HistoryAppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, HistoryAppDatabase.class, "HistoryDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}

