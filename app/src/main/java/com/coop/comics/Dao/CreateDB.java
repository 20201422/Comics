package com.coop.comics.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public CreateDB(Context context) {
        super(context, "comics.db", null, 1);
    }
    private static final String dbCreateBook="create table book(" +
            "book_id integer primary key autoincrement" +
            ",book_name text" +
            ",book_photo integer)";
    private static final String dbCreatePages="create table pages(" +
            "imageResId integer " +
            ",bookId integer" +
            ",title text" +
            ",summary text" +
            ",page integer" +
            ",isCollection integer" +
            ",isBookmark integer)";
    private static final String dbCreateCollection = "create table collection(" +
            "collectionId integer " +
            ",book_id integer" +
            ",book_name text" +
            ",page_id integer" +
            ",s_number integer primary key autoincrement)";
    private static final String dbCreateBookmark = "create table bookmark(" +
            "imageResId integer" +
            ",bookmarkId integer" +
            ",book_id integer" +
            ",book_name text" +
            ",page_id integer)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(dbCreateBook);
        sqLiteDatabase.execSQL(dbCreatePages);
        sqLiteDatabase.execSQL(dbCreateCollection);
        sqLiteDatabase.execSQL(dbCreateBookmark);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase getDb() {
        db = getWritableDatabase();
        return db;
    }
}
