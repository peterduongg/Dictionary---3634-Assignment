package com.example.dictionary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Other.DictionaryAdapter;
import com.example.dictionary.Object.History;
import com.example.dictionary.R;
import com.example.dictionary.Other.DictionaryRequest;
import com.example.dictionary.database.HistoryAppDatabase;

public class DictionaryActivity extends AppCompatActivity {
    //string used to build
    String url;
    private Button defineButton;
    private TextView definitionTextView;
    private static EditText enterWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        definitionTextView = findViewById(R.id.definition);
        enterWord = findViewById(R.id.searchWord);
        defineButton = findViewById(R.id.searchButtonXML);
        defineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterWord.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter text into search field", Toast.LENGTH_SHORT).show();
                } else{
                    sendRequestOnClick();
                }
            }
        });

    }

    public String dictionaryEntries() {
        final String language = "en-gb";
        String word = enterWord.getText().toString();

        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public static void addHistory(Context context) {
        HistoryAppDatabase historyAppDatabase;
        historyAppDatabase = HistoryAppDatabase.getInstance(context);
        History history = new History();
        String word = enterWord.getText().toString();
        history.setWord(word);
        historyAppDatabase.historyDao().insert(history);
    }

    public void sendRequestOnClick(){
        definitionTextView.setText("Loading...");
        DictionaryRequest dr = new DictionaryRequest(this,definitionTextView);
        url = dictionaryEntries();
        dr.execute(url);

    }

    }

