package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.coop.comics.Model.Book;
import com.coop.comics.Model.Bookmark;
import com.coop.comics.Model.ComicData;

import java.util.ArrayList;

public class BookmarkDao extends SQLiteOpenHelper {
    public BookmarkDao(Context context) {
        super(context, "comics.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 查询所有书签
     * @return
     */
    public ArrayList<Bookmark> queryAllBookmark(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Bookmark> list = new ArrayList<Bookmark>();

        Cursor results = db.query("bookmark",null,null,null,null,null,null);
        int resultCounts = results.getCount();

        if(resultCounts==0||!results.moveToFirst()){
            return list;
        }

        for(int i=0;i<resultCounts;i++){
            @SuppressLint("Range")
            Bookmark bookmark = new Bookmark(results.getInt(0),results.getInt(1),results.getInt(2),results.getString(3),results.getInt(4));
            list.add(bookmark);
            results.moveToNext();
        }

        return list;
    }
}
