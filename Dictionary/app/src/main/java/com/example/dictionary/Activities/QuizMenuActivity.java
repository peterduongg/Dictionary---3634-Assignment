package com.example.dictionary.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.R;

public class QuizMenuActivity extends AppCompatActivity {
    Button buttonStartMediumQuiz;
    Button backButton;
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String HIGHSCORE_KEY = "highScoreKey";
    private TextView highScoreTextView;
    private int highScore;

    public QuizMenuActivity(){
        //empty constructor to refer to
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        highScoreTextView = findViewById(R.id.mediumScoreText);
        loadHighScore();

        buttonStartMediumQuiz = findViewById(R.id.mediumQuizButton);
        buttonStartMediumQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });

    }

    private void startQuiz() {
        Intent intent = new Intent(QuizMenuActivity.this, QuizQuestionsActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if(requestCode == RESULT_OK){
                int score = data.getIntExtra(QuizQuestionsActivity.SCORE_RETURN, 0);
                if (score > highScore){
                    updateHighScore(score);
                }
            }
        }
    }

    private void updateHighScore(int newHighScore) {
        highScore = newHighScore;
        highScoreTextView.setText("Highscore:" + highScore);
        SharedPreferences pref = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(HIGHSCORE_KEY, highScore);
        editor.apply();
    }

    private void loadHighScore(){
        SharedPreferences pref = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        highScore = pref.getInt(HIGHSCORE_KEY, 0);
        highScoreTextView.setText("Highscore: " + highScore);
    }

    private void returnToMain() {
        Intent intent = new Intent(QuizMenuActivity.this, MainActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }
}