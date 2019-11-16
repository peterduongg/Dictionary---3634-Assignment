package com.example.dictionary.database;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){}

    public static class QuestionTable implements BaseColumns {

        //defining the elements of each of the quiz questions.
        public static final String TABLE_NAME = "medium_quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";

        public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    }
}
