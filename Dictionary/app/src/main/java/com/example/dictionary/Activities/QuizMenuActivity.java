package com.example.dictionary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dictionary.R;

public class QuizMenuActivity extends AppCompatActivity {
    Button buttonStartEasyQuiz;
    Button buttonStartMediumQuiz;
    Button buttonStartHardQuiz;
    Button backButton;


    public QuizMenuActivity(){
        //empty constructor to refer to
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //activity linking to the 3 quiz difficulties
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        buttonStartEasyQuiz = findViewById(R.id.easyQuizButton);
        buttonStartEasyQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizEasy();
            }
        });

        buttonStartMediumQuiz = findViewById(R.id.mediumQuizButton);
        buttonStartMediumQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizMedium();
            }
        });

        buttonStartHardQuiz = findViewById(R.id.hardQuizButton);
        buttonStartHardQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuizHard();
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

    //calling relevant methods to start intents
    private void startQuizEasy() {
        Intent intent = new Intent(QuizMenuActivity.this, QuizEasyQuestionsActivity.class);
        startActivity(intent);

    }

    private void startQuizMedium() {
        Intent intent = new Intent(QuizMenuActivity.this, QuizMediumQuestionsActivity.class);
        startActivity(intent);

    }

    private void startQuizHard() {
        Intent intent = new Intent(QuizMenuActivity.this, QuizHardQuestionsActivity.class);
        startActivity(intent);

    }

    private void returnToMain() {
        Intent intent = new Intent(QuizMenuActivity.this, MainActivity.class);
        startActivity(intent);
    }



}
