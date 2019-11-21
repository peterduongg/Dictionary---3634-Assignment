package com.example.dictionary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.database.HistoryAppDatabase;

class HistoryDetailActivity extends AppCompatActivity {

    RecyclerView historyRecyclerView;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dictionary_history);
//
//        historyRecyclerView = findViewById(R.id.dictionaryHistory_rv);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        historyRecyclerView.setLayoutManager(layoutManager);
//
//        final DictionaryAdapter dictionaryAdapter = new DictionaryAdapter(this);
//        final HistoryAppDatabase historyAppDatabase = HistoryAppDatabase.getInstance(this);
//
//        dictionaryAdapter.setData(historyAppDatabase.historyDao().getAllHistory());
//
//        historyRecyclerView.setAdapter(dictionaryAdapter);
    }

}
