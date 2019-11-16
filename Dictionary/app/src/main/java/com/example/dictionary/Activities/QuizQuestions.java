package com.example.dictionary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dictionary.Object.Question;
import com.example.dictionary.R;
import com.example.dictionary.database.QuizDatabaseHelper;

import java.util.List;

public class QuizQuestions extends AppCompatActivity {

    private TextView questionTextView;
    private TextView scoreTextView;
    private TextView questionCountTextView;
    private RadioGroup radioGroup;
    private RadioButton rbl;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button confirmButton;

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);


        questionTextView = findViewById(R.id.question_TextView);
        scoreTextView = findViewById(R.id.score_TextView);
        questionCountTextView = findViewById(R.id.question_CountTextView);
        radioGroup = findViewById(R.id.radio_Group);
        rbl = findViewById(R.id.radio_Button1);
        rb2 = findViewById(R.id.radio_Button2);
        rb3 = findViewById(R.id.radio_Button3);
        confirmButton = findViewById(R.id.confirm_SelectionButton);

        QuizDatabaseHelper databaseHelper = new QuizDatabaseHelper(this);

        questionList = databaseHelper.getAllQuestions();

    }
}
