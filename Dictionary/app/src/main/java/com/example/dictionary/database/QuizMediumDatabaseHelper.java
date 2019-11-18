package com.example.dictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dictionary.Object.Question;
import com.example.dictionary.database.QuizContract.*;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class QuizMediumDatabaseHelper extends SQLiteOpenHelper {

    //database that will contain the quiz
    private static final String DATABASE_NAME = "MediumQuizNEW.db";
    //defining the version of the database
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizMediumDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating the initial database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //used to add values to database later
        this.db = db;

        //sqlite code to create database (won't be called again unless the app is deleted)
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_CORRECT_ANSWER + " INTEGER"  +
                ")";

        //passing the SQLite code from above into a database
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();


    }

    //if we are to update the database, this update method is called
    //it will drop any existing table and then call the 'oncreate' method again
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    //Defining the correct answer for the questions
    private void fillQuestionTable(){
        Question q1 = new Question("Define the word esophagus (A Correct)", "A", "B", "C", 1);
        addQuestion(q1);
        Question q2 = new Question("Define the word esophagus (B Correct)", "A", "B", "C", 2);
        addQuestion(q2);
        Question q3 = new Question("Define the word esophagus (C Correct)", "A", "B", "C", 3);
        addQuestion(q3);
        Question q4 = new Question("Define the word esophagus (B Correct)", "A", "B", "C", 2);
        addQuestion(q4);
        Question q5 = new Question("Define the word esophagus (A Correct)", "A", "B", "C", 1);
        addQuestion(q5);
    }

    //
    private void addQuestion(Question question){
        //
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_CORRECT_ANSWER, question.getCorrectAnswer());
        //inserting the above values into the database
        db.insert(QuestionTable.TABLE_NAME, null, cv);

        System.out.println("added to database");
    }

    //to retrieve data from a different class
    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();
        //Below cursor is used to query the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        //Only querying from database is there is an existing entry in the database
        if (cursor.moveToFirst()){
            do {
                Question question = new Question();
                // getting the index of where each attribute/column is saved
                // (we're trying not to hard coding it by actively finding the location of the column using 'getColumnIndex')
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex((QuestionTable.COLUMN_OPTION1))));
                question.setOption2(cursor.getString(cursor.getColumnIndex((QuestionTable.COLUMN_OPTION2))));
                question.setOption3(cursor.getString(cursor.getColumnIndex((QuestionTable.COLUMN_OPTION3))));
                question.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex((QuestionTable.COLUMN_CORRECT_ANSWER))));
                // add each of  these to the 'question' array
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
