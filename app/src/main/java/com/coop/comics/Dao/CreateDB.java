package com.coop.comics.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDB extends SQLiteOpenHelper {
    public SQLiteDatabase db;

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
            ",page int)";
    private static final String dbCreateCollection = "create table collection(" +
            "id integer primary key autoincrement" +
            ",book_id integer" +
            ",page_id integer)";
    private static final String dbCreateBookmark = "create table bookmark(" +
            "id integer primary key autoincrement" +
            ",book_id integer" +
            ",page_id integer)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(dbCreateBook);
        sqLiteDatabase.execSQL(dbCreatePages);
        sqLiteDatabase.execSQL(dbCreateCollection);
        sqLiteDatabase.execSQL(dbCreateBookmark);

    }

    public SQLiteDatabase getDb(){  //获取数据库
        db = this.getWritableDatabase();
        return db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
