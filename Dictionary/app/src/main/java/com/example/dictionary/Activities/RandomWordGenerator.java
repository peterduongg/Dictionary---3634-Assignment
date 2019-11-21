package com.example.dictionary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dictionary.R;
import com.example.dictionary.database.WordGeneratorDatabase;

public class RandomWordGenerator extends AppCompatActivity {

    private WordGeneratorDatabase wordGeneratorDatabase = new WordGeneratorDatabase();
    TextView wordTextView;
    TextView generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_word_generator);
        wordTextView = findViewById(R.id.word_display);
        generateButton = findViewById(R.id.generate_button);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayWord = WordGeneratorDatabase.getRandomWords();
                wordTextView.setText(displayWord);
            }
        });

    }
}
