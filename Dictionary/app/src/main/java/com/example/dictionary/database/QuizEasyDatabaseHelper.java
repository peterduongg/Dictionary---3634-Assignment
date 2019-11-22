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

//references quiz tutorial https://www.youtube.com/watch?v=PiCZQg4mhBQ&t
public class QuizEasyDatabaseHelper extends SQLiteOpenHelper {

    //database that will contain the quiz
    private static final String DATABASE_NAME = "EasyQuiz.db";
    //defining the version of the database
    private static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;

    public QuizEasyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating the initial database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //used to add values to database later
        this.db = db;

        //sqlite code to create database
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

    //if database version is incremented, it will update existing FB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    //Defining the correct answer for the questions
    private void fillQuestionTable(){

        //prefilling questions and answers
        Question q1 = new Question(
                "Define the word Potato",
                "a round white vegetable with a brown or red skin that grows underground as the root of a plant",
                "a round fruit with shiny red or green skin and firm white flesh",
                "two slices of bread, often spread with butter, with a layer of meat, cheese, etc. between them", 1);
        addQuestion(q1);
        Question q2 = new Question("Define the word Apple",
                "a round citrus fruit with thick reddish-yellow skin and a lot of sweet juice",
                "a round fruit with shiny red or green skin and firm white flesh",
                "a round white vegetable with a brown or red skin that grows underground as the root of a plant",
                2);
        addQuestion(q2);
        Question q3 = new Question(
                "Define the word Dog",
                "a large wild animal of the cat family, that has yellowish fur with black lines and lives in parts of Asia",
                "a large bird that is often kept for its eggs or meat",
                "an animal with four legs and a tail, often kept as a pet or trained for work, for example hunting or guarding buildings.",
                3);
        addQuestion(q3);
        Question q4 = new Question(
                "Define the word Pencil",
                "an object made of short stiff hairs (called bristles ) or wires set in a block of wood or plastic, usually attached to a handle.",
                "a narrow piece of wood, or a metal or plastic case, containing a black or coloured substance, used for drawing or writing",
                "a long thin piece of strong hard material, especially wood, used, for example, for making floors, building walls and roofs and making boats",
                2);
        addQuestion(q4);
        Question q5 = new Question(
                "Define the word Card",
                "thick stiff paper",
                "stiff material like very thick paper, often used for making boxes",
                "the hard material that the trunk and branches of a tree are made of; this material when it is used to build or make things with, or as a fuel",
                1);
        addQuestion(q5);
        Question q6 = new Question(
                "Define the word Fat",
                "having too much flesh on it and weighing too much",
                "large in size, degree, amount, etc",
                "shaped like a circle or a ball",
                1);
        addQuestion(q6);
        Question q7 = new Question(
                "Define the word Phone",
                "an electronic machine that can store, organize and find information, do calculations and control other machines",
                "a system for talking to somebody else over long distances using wires or radio; a machine used for this; a telephone",
                "an organized set of ideas or theories or a particular way of doing something",
                2);
        addQuestion(q7);
        Question q8 = new Question(
                "Define the word Cable",
                "very strong thick string made by twisting thinner strings, wires, etc. together",
                "material made of several threads twisted together, used for tying things together; a piece of X used to fasten or pull something or keep something in place",
                "thick strong metal rope used on ships, for supporting bridges, etc.",
                3);
        addQuestion(q8);
        Question q9 = new Question(
                "Define the word Book",
                "a section of a piece of writing, usually consisting of several sentences dealing with a single subject.",
                "a set of printed pages that are fastened inside a cover so that you can turn them and read them",
                "a single unit of language which means something and can be spoken or written",
                2);
        addQuestion(q9);
        Question q10 = new Question(
                "Define the word DictionaryActivity",
                "a book that gives a list of the words of a language in alphabetical order and explains what they mean, or gives a word for them in a foreign language",
                "an explanation of the meaning of a word or phrase, the act of stating the meanings of words and phrases",
                "a series of names, items, figures, etc., especially when they are written or printed",
                1);
        addQuestion(q10);
    }

    //
    private void addQuestion(Question question){
        //placing each of the table elements into the quiz_questions.xml
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
