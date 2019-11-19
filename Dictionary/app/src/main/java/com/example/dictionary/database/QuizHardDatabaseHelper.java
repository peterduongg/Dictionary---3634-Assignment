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

public class QuizHardDatabaseHelper extends SQLiteOpenHelper {

    //database that will contain the quiz
    private static final String DATABASE_NAME = "HardQuiz.db";
    //defining the version of the database
    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase db;

    public QuizHardDatabaseHelper(@Nullable Context context) {
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
        Question q1 = new Question(
                "Define the word gemmology",
                "the study of precious stones",
                "a religious system based on getting knowledge of yourself and spiritual fulfilment through courses of study and training",
                "a tropical fruit with smooth yellow or red skin, soft orange flesh and a large seed inside",
                1);
        addQuestion(q1);
        Question q2 = new Question("Define the word Anaesthesia ",
                "lack of feeling within a body part",
                "insensitivity to pain, especially as of gases or the injection of drugs before surgical operations",
                "the part of the brain controlling the eyes, nose, mouth and brain",
                2);
        addQuestion(q2);
        Question q3 = new Question(
                "Define the word Bibulous",
                " connected with religion or with a particular religion",
                "to help or encourage somebody/something by saying or showing that you agree with them/it",
                "liking to drink too much alcohol",
                3);
        addQuestion(q3);
        Question q4 = new Question(
                "Define the word Malapropism",
                "the part of the body on top of the neck containing the eyes, nose, mouth and brain",
                "an amusing mistake somebody makes when they use a word which sounds similar to the word they wanted to use, but means something different",
                "tending to find and call attention to faults",
                2);
        addQuestion(q4);
        Question q5 = new Question(
                "Define the word Tricorn",
                "cocked hat with the brim turned up to form three points",
                "a large dinosaur with two large horns and one small horn on its very large head",
                "the hard material that the trunk and branches of a tree are made of; this material when it is used to build or make things with, or as a fuel",
                1);
        addQuestion(q5);
        Question q6 = new Question(
                "Define the word Tenebrous",
                "dark; shadowy or obscure",
                "willing to do things which are difficult, dangerous or painful; not afraid",
                "not large in size, number, degree, amount, etc.",
                1);
        addQuestion(q6);
        Question q7 = new Question(
                "Define the word Bruit",
                "having a lot of physical power so that you can lift heavy weights, do hard physical work, etc.",
                "to spread a piece of news widely",
                "an organized set of ideas or theories or a particular way of doing something",
                2);
        addQuestion(q7);
        Question q8 = new Question(
                "Define the word Pother",
                "a series of movements and steps that are usually performed to music; a particular example of these movements and steps",
                "situation in which there is an angry disagreement between people or groups who have different opinions",
                "a commotion or fuss",
                3);
        addQuestion(q8);
        Question q9 = new Question(
                "Define the word Cantle",
                "a leather seat for a rider on a horse",
                "a lack of agreement between facts, opinions, actions, etc.",
                "the raised curved part of the back of a horse's saddle",
                2);
        addQuestion(q9);
        Question q10 = new Question(
                "Define the word Regnant",
                "having a baby or young animal developing inside her/its body",
                "exercising power or authority",
                "a series of names, items, figures, etc., especially when they are written or printed",
                1);
        addQuestion(q10);
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
