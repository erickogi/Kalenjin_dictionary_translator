package com.erickogi14gmail.kale.Dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.erickogi14gmail.kale.Learn.LessonPojo;
import com.erickogi14gmail.kale.Notes.NotesPojo;

import java.util.ArrayList;

/**
 * Created by Eric on 10/17/2017.
 */

public class DbOperation  {
    private Context context;
    private DbClass dbHandler;
    private boolean successful = false;
    private double returnValueDouble = 0.0;

    public DbOperation(Context context) {
        dbHandler = new DbClass(context);
        this.context = context;
    }

    public ArrayList<WordPojo> searchWord(int id) {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        ArrayList<WordPojo> data = new ArrayList<>();
        String QUERY = null;


                QUERY = "SELECT * FROM  kale_words WHERE  entry_id ='"+id+"'";



        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                WordPojo pojo = new WordPojo();
                pojo.setEntry_id(cursor.getInt(0));
                pojo.setWord_kale(cursor.getString(1));
                pojo.setWord_english(cursor.getString(2));
                pojo.setWord_kiswahili(cursor.getString(3));
                pojo.setWord_english_description(cursor.getString(4));
                pojo.setWord_kale_description(cursor.getString(5));
                pojo.setWord_view_count(cursor.getInt(6));
                pojo.setWord_view_last(cursor.getString(7));
                pojo.setWord_favourite_status(cursor.getString(8));
                pojo.setWord_others(cursor.getString(9));

                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;


    }
    public ArrayList<WordPojo> searchAllWord() {
        //Open connection to read only
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        ArrayList<WordPojo> data = new ArrayList<>();
        String QUERY = null;


        QUERY = "SELECT * FROM  kale_words ";



        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                WordPojo pojo = new WordPojo();
                pojo.setEntry_id(cursor.getInt(0));
                pojo.setWord_kale(cursor.getString(1));
                pojo.setWord_english(cursor.getString(2));
                pojo.setWord_kiswahili(cursor.getString(3));
                pojo.setWord_english_description(cursor.getString(4));
                pojo.setWord_kale_description(cursor.getString(5));
                pojo.setWord_view_count(cursor.getInt(6));
                pojo.setWord_view_last(cursor.getString(7));
                pojo.setWord_favourite_status(cursor.getString(8));
                pojo.setWord_others(cursor.getString(9));

                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;


    }
    public boolean insertWord(WordPojo data) {
        boolean success = false;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("word_kale", data.getWord_kale());


        values.put("word_english", data.getWord_english());
        values.put("word_kiswahili", data.getWord_kiswahili());
        values.put("word_english_description", data.getWord_english_description());
        values.put("word_kale_description", data.getWord_kale_description());
        values.put("word_view_count", data.getWord_view_count());
        values.put("word_view_last", data.getWord_view_last());
        values.put("word_favorite_status", data.getWord_favourite_status());
        values.put("word_others", data.getWord_others());


        if (db.insert("kale_words", null, values) >= 1) {
            success = true;
        }
        db.close();


        return success;


    }
    public boolean insertLessons(LessonPojo data) {
        boolean success = false;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("progress", data.getProgress());


        values.put("title", data.getTitle());

        values.put("lesson_id",data.getLesson_id());



        if (db.insert("kale_lessons", null, values) >= 1) {
            success = true;
        }
        db.close();


        return success;


    }
    public boolean insertNote(NotesPojo data) {
        boolean success = false;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("note_title", data.getNote_title());


        values.put("note_content", data.getNote_content());
        values.put("note_date", data.getNote_date());

        if (db.insert("kale_notes", null, values) >= 1) {
            success = true;
        }
        db.close();


        return success;


    }
    public ArrayList<ListPojo> searchWords(String search,int lang){
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        ArrayList<ListPojo> data = new ArrayList<>();
        String QUERY = null;

        switch (lang){
            case 1:
                QUERY = "SELECT * FROM  kale_words WHERE  word_english LIKE '%" + search + "%'";
                break;
            case 2:
                QUERY = "SELECT * FROM  kale_words WHERE  word_kale LIKE '%" + search + "%'";
                break;
            case 3:
                QUERY = "SELECT * FROM  kale_words WHERE  word_kiswahili LIKE '%" + search + "%'";
                break;
        }


        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                ListPojo pojo = new ListPojo();



                pojo.setEntry_id(cursor.getInt(0));
                pojo.setWord_english(cursor.getString(2));
                pojo.setWord_english_description(cursor.getString(4));
                pojo.setWord_kale(cursor.getString(1));
                pojo.setWord_kale_description(cursor.getString(5));


                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;

    }
    public ArrayList<ListPojo> searchFavorites(String search){
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        ArrayList<ListPojo> data = new ArrayList<>();
        String QUERY = null;
        String status="1";

                QUERY = "SELECT * FROM  kale_words WHERE  word_english LIKE '%" + search + "%' AND word_favorite_status ='"+status+"'";




        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                ListPojo pojo = new ListPojo();



                pojo.setEntry_id(cursor.getInt(0));
                pojo.setWord_english(cursor.getString(2));
                pojo.setWord_english_description(cursor.getString(4));
                pojo.setWord_kale(cursor.getString(1));
                pojo.setWord_kale_description(cursor.getString(5));


                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;

    }
    public ArrayList<NotesPojo> searchNotes(String search){
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        ArrayList<NotesPojo> data = new ArrayList<>();
        String QUERY = null;
        String status="1";

        QUERY = "SELECT * FROM  kale_notes WHERE  note_title LIKE '%" + search + "%' ";




        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                NotesPojo pojo = new NotesPojo();



                pojo.setEntry_id(cursor.getInt(0));
                pojo.setNote_title(cursor.getString(1));
                pojo.setNote_content(cursor.getString(2));
                pojo.setNote_date(cursor.getString(3));


                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;

    }
    public ArrayList<LessonPojo> searchLessons(String search){
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        ArrayList<LessonPojo> data = new ArrayList<>();
        String QUERY = null;
        String status="1";

        QUERY = "SELECT * FROM  kale_lessons WHERE  title LIKE '%" + search + "%' ";




        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {
                LessonPojo pojo = new LessonPojo();



                pojo.setProgress(cursor.getInt(1));
                pojo.setTitle(cursor.getString(2));
                pojo.setLesson_id(cursor.getString(3));


                data.add(pojo);

            }
        }
        db.close();
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return data;

    }
    public boolean setFavorite(int id, String chan) {
        try {
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("word_favorite_status", chan);

            if (db.update("kale_words", cv, "entry_id ='" + id + "'", null) > 0) {
                successful = true;
            } else {
                successful = false;
            }
            db.close();
            return successful;


        } catch (Exception m) {
            m.printStackTrace();
            Log.d("mjk", m.toString());
            return false;
        }
    }
    public boolean updateNote(int id, String title,String content) {
        try {
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("note_title", title);
            cv.put("note_content", content);

            if (db.update("kale_notes", cv, "entry_id ='" + id + "'", null) > 0) {
                successful = true;
            } else {
                successful = false;
            }
            db.close();
            return successful;


        } catch (Exception m) {
            m.printStackTrace();
            Log.d("mjk", m.toString());
            return false;
        }
    }
    public boolean removeFromFavorite(int id){
        try {
            SQLiteDatabase db = dbHandler.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("word_favorite_status", "0");

            if (db.update("kale_words", cv, "entry_id ='" + id + "'", null) > 0) {
                successful = true;
            } else {
                successful = false;
            }
            db.close();
            return successful;


        } catch (Exception m) {
            m.printStackTrace();
            Log.d("mjk", m.toString());
            return false;
        }
    }
    public boolean deleteNote(int id){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        if (db.delete("kale_notes", "entry_id" + "= '" + id+ "' ", null) > 0) {
            successful = true;
        } else {
            successful = false;
        }
        db.close();
        return successful;
    }

}
