package com.example.dictionary.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.DictionaryAdapter;
import com.example.dictionary.Object.HistoryDao;
import com.example.dictionary.R;
import com.example.dictionary.database.HistoryAppDatabase;

public class DictionaryHistory extends AppCompatActivity {
    public RecyclerView historyRV;
    Button clearDatabase;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_history);
        historyRV = findViewById(R.id.dictionaryHistory_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        historyRV.setLayoutManager(layoutManager);
//        clearDatabase = findViewById(R.id.buttonClearHistory);



        final DictionaryAdapter dictionaryAdapter = new DictionaryAdapter(this);
        final HistoryAppDatabase db = HistoryAppDatabase.getInstance(getApplicationContext());

        dictionaryAdapter.setData(db.historyDao().getAllHistory());
        historyRV.setAdapter(dictionaryAdapter);

//        clearDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db.execSQL("DROP TABLE IF EXISTS")
//            }
//        });

    }
}
