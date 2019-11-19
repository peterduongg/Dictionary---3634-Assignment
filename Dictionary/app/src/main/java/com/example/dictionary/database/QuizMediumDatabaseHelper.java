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
    private static final String DATABASE_NAME = "MediumQuiz.db";
    //defining the version of the database
    private static final int DATABASE_VERSION = 3;

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
        Question q1 = new Question(
                "Define the word Sphere",
                "a solid figure that is completely round, with every point on its surface at an equal distance from the centre",
                "an animal with four legs and a tail, often kept as a pet or trained for work, for example hunting or guarding buildings.",
                "a tropical fruit with smooth yellow or red skin, soft orange flesh and a large seed inside",
                1);
        addQuestion(q1);
        Question q2 = new Question("Define the word Assembly",
                "existing or happening now",
                "a group of people who have been elected to meet together regularly and make decisions or laws for a particular region or country",
                "a US film actor and later Republican politician who became the 40th US President",
                2);
        addQuestion(q2);
        Question q3 = new Question(
                "Define the word Throne",
                "the soft part of a bed, that you lie on",
                "a seat with legs but with nothing to support your back or arms",
                "a special chair used by a king or queen to sit on at ceremonies",
                3);
        addQuestion(q3);
        Question q4 = new Question(
                "Define the word Domination",
                "the part of the body on top of the neck containing the eyes, nose, mouth and brain",
                "control or power over somebody/something, especially in an unpleasant way",
                "a long thin piece of strong hard material, especially wood, used, for example, for making floors, building walls and roofs and making boats",
                2);
        addQuestion(q4);
        Question q5 = new Question(
                "Define the word Conservative",
                "opposed to great or sudden social change; showing that you prefer traditional styles and values",
                "to keep somebody/something safe from death, harm, loss, etc.",
                "the hard material that the trunk and branches of a tree are made of; this material when it is used to build or make things with, or as a fuel",
                1);
        addQuestion(q5);
        Question q6 = new Question(
                "Define the word Reduction",
                "an act of making something less or smaller; the state of being made less or smaller",
                "large in size, degree, amount, etc",
                "not large in size, number, degree, amount, etc.",
                1);
        addQuestion(q6);
        Question q7 = new Question(
                "Define the word Legislature",
                "an electronic machine that can store, organize and find information, do calculations and control other machines",
                "a group of people who have the power to make and change laws",
                "an organized set of ideas or theories or a particular way of doing something",
                2);
        addQuestion(q7);
        Question q8 = new Question(
                "Define the word Confrontation",
                "a series of movements and steps that are usually performed to music; a particular example of these movements and steps",
                "material made of several threads twisted together, used for tying things together; a piece of X used to fasten or pull something or keep something in place",
                "situation in which there is an angry disagreement between people or groups who have different opinions",
                3);
        addQuestion(q8);
        Question q9 = new Question(
                "Define the word Contraction",
                "to take part in a war or battle against an enemy",
                "the process of becoming smaller",
                "a single unit of language which means something and can be spoken or written",
                2);
        addQuestion(q9);
        Question q10 = new Question(
                "Define the word Consensus",
                "an opinion that all members of a group agree with",
                "an explanation of the meaning of a word or phrase, the act of stating the meanings of words and phrases",
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
