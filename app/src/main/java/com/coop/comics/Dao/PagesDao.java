package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.coop.comics.Model.Book;
import com.coop.comics.Model.ComicData;

import java.util.ArrayList;

public class PagesDao extends SQLiteOpenHelper {
    public PagesDao(Context context) {
        super(context, "comics.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 根据bookId查询每一页内容
     * @param id
     * @return
     */
    public ArrayList<ComicData> queryByBookId(int id){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ComicData> pages = new ArrayList<ComicData>();
        String sql = "select * from pages where bookId="+id;
        Cursor cursor = db.rawQuery(sql,null);
        int resultCounts = cursor.getCount();
        System.out.println("length:"+resultCounts);
        if(resultCounts==0||!cursor.moveToFirst()){
            return pages;
        }

        for(int i=0;i<resultCounts;i++){
            @SuppressLint("Range")
            ComicData comicData = new ComicData(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4));
            pages.add(comicData);
            cursor.moveToNext();
        }
        return pages;
    }

    /**
     * 根据pageId将该页添加进收藏
     * @param id
     */
    public void addToCollection(int id){
        SQLiteDatabase db = getWritableDatabase();
    }

    /**
     * 根据pageId将该页添加进书签
     * @param id
     */
    public void addToBookmark(int id){
        SQLiteDatabase db = getWritableDatabase();
    }

}