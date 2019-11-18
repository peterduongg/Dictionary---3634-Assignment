package com.example.dictionary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionary.Object.Question;
import com.example.dictionary.R;
import com.example.dictionary.database.QuizHardDatabaseHelper;

import java.util.Collections;
import java.util.List;

public class QuizHardQuestionsActivity extends AppCompatActivity {

//    public static final String SCORE_RETURN = "Score return";

    private TextView questionTextView;
    private TextView scoreTextView;
    private TextView questionCountTextView;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button confirmButton;

    private List<Question> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private ColorStateList textColorDefaultRb;
    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_questions);


        questionTextView = findViewById(R.id.question_TextView);
        scoreTextView = findViewById(R.id.score_TextView);
        questionCountTextView = findViewById(R.id.question_CountTextView);
        radioGroup = findViewById(R.id.radio_Group);
        rb1 = findViewById(R.id.radio_Button1);
        rb2 = findViewById(R.id.radio_Button2);
        rb3 = findViewById(R.id.radio_Button3);
        confirmButton = findViewById(R.id.confirm_SelectionButton);

        textColorDefaultRb = rb1.getTextColors();

        QuizHardDatabaseHelper dbHelper = new QuizHardDatabaseHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(v.getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            questionTextView.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            questionCountTextView.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            confirmButton.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNumber = radioGroup.indexOfChild(rbSelected) + 1;

        if (answerNumber == currentQuestion.getCorrectAnswer()) {
            score++;
            scoreTextView.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnswer()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                questionTextView.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            confirmButton.setText("Next");
        } else {
            confirmButton.setText("Finish");
        }
    }

    private void finishQuiz() {

        finish();
    }
}