package com.example.dictionary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.DictionaryAdapter;
import com.example.dictionary.Object.History;
import com.example.dictionary.R;
import com.example.dictionary.database.DictionaryRequest;
import com.example.dictionary.database.HistoryAppDatabase;

import java.util.List;

public class Dictionary extends AppCompatActivity {
    String url;
    private Button defineButton;
    private TextView definitionTextView;
    private EditText enterWord;
    HistoryAppDatabase historyAppDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        definitionTextView = findViewById(R.id.definition);
        enterWord = findViewById(R.id.searchWord);
        defineButton = findViewById(R.id.searchButtonXML);
        final DictionaryAdapter dictionaryAdapter = new DictionaryAdapter(getApplicationContext());
        defineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestOnClick();
            }
        });

    }
    public String dictionaryEntries() {
        final String language = "en-gb";
        String word = enterWord.getText().toString();

        historyAppDatabase = HistoryAppDatabase.getInstance(this);
        History history = new History();
        history.setWord(word);
        if (history != null){
            historyAppDatabase.historyDao().insert(history);
            Toast.makeText(this, "Added to history", Toast.LENGTH_SHORT).show();
        }


        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public void sendRequestOnClick(){
        definitionTextView.setText("Loading...");
        DictionaryRequest dr = new DictionaryRequest(this,definitionTextView);
        url = dictionaryEntries();
        dr.execute(url);


    }



    }

