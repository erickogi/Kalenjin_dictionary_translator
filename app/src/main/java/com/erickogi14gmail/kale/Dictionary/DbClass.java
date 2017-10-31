package com.erickogi14gmail.kale.Dictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric on 10/17/2017.
 */

public class DbClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kale.db";

    String createTableWords = "CREATE TABLE `kale_words` (" +
            "  `entry_id` INTEGER PRIMARY KEY AUTOINCREMENT  ," +
            "  `word_kale` varchar NOT NULL," +
            "  `word_english` varchar NOT NULL," +
            "  `word_kiswahili` varchar ," +
            "  `word_english_description` varchar ," +
            "  `word_kale_description` varchar ," +
            "  `word_view_count` varchar ," +
            "  `word_view_last` datetime  ," +
            "  `word_favorite_status` varchar  ," +
            "  `word_others` varchar  "

            + ")";

    String createTableLearn = "CREATE TABLE `kale_learn` (" +
            "  `entry_id` INTEGER PRIMARY KEY AUTOINCREMENT  ," +


            "  `learn_content_title` varchar ,"+
            "  `learn_content_kale` varchar ," +
            "   `learn_content_english` varchar  "


            + ")";
    String createTableNotes = "CREATE TABLE `kale_notes` (" +
            "  `entry_id` INTEGER PRIMARY KEY AUTOINCREMENT  ," +


            "  `note_title` varchar ,"+
            "  `note_content` varchar ," +
            "   `note_date` varchar  "


            + ")";

    String createTableLesson = "CREATE TABLE `kale_lessons` (" +
            "  `entry_id` INTEGER PRIMARY KEY AUTOINCREMENT  ," +


            "  `progress` varchar ,"+
            "  `title` varchar ," +
            "   `lesson_id` varchar  "


            + ")";


    String createTableQuiz = "CREATE TABLE `kale_quiz` (" +
            "  `entry_id` INTEGER PRIMARY KEY AUTOINCREMENT  ," +
            "  `learn_id` INTEGER   ," +


            "  `quiz_content_title` varchar ,"+
            "  `quiz_content_question` varchar ," +
            "  `quiz_ans_A` varchar ," +
            "  `quiz_ans_B` varchar ," +
            "  `quiz_ans_C` varchar ," +
            "  `quiz_ans_D` varchar ," +

            "  `quiz_ans_Correct` varchar  "


            + ")";





    public DbClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableWords);
        db.execSQL(createTableLearn);
        db.execSQL(createTableQuiz);
        db.execSQL(createTableNotes);
        db.execSQL(createTableLesson);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + createTableWords);
        db.execSQL("DROP TABLE IF EXISTS " + createTableLearn);
        db.execSQL("DROP TABLE IF EXISTS " + createTableQuiz);
        db.execSQL("DROP TABLE IF EXISTS " + createTableNotes);
        db.execSQL("DROP TABLE IF EXISTS " + createTableLesson);



        onCreate(db);
    }

}
