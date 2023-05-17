package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.coop.comics.Model.Book;
import com.coop.comics.Model.ComicData;

import java.util.ArrayList;
import java.util.List;

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
            comicData.setCollection(cursor.getInt(5));
            comicData.setBookmark(cursor.getInt(6));
            pages.add(comicData);
            cursor.moveToNext();
        }
        return pages;
    }

    /**
     * 根据pageId将该页添加进收藏
     * @param comicData
     */
    public void addToCollection(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from book where book_id = "+comicData.getBookId();
        Cursor cursor = db.rawQuery(sql,null);  //获取对应的书
        int resultCounts = cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return ;
        }
        ContentValues newValues = new ContentValues();   //存放收藏内容
        newValues.put("collectionId",comicData.getImageResId());
        newValues.put("book_id",comicData.getBookId());
        newValues.put("book_name",cursor.getString(1));
        newValues.put("page_id",comicData.getPage());
        db.insert("collection",null,newValues);  //插入数据
        updatePageCollect(comicData);  //更新状态
    }

    /**
     * 删除某个收藏
     * @param comicData
     */
    public void delCollection(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from collection where collectionId = "+comicData.getImageResId();
        db.execSQL(sql);
        updatePageNotCollect(comicData);
    }

    /**
     * 根据pageId将该页添加进书签
     * @param comicData
     */
    public void addToBookmark(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from book where book_id = "+comicData.getBookId();
        Cursor cursor = db.rawQuery(sql,null);  //获取对应的书
        int resultCounts = cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return ;
        }
        ContentValues newValues = new ContentValues();   //存放书签内容
        newValues.put("bookmarkId",comicData.getImageResId());
        newValues.put("book_id",comicData.getBookId());
        newValues.put("book_name",cursor.getString(1));
        newValues.put("page_id",comicData.getPage());
        db.insert("bookmark",null,newValues);  //插入数据
        updatePageMark(comicData);  //更新状态
    }

    /**
     * 删除某个书签
     */
    public void delMark(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from bookmark where bookmarkId = "+comicData.getImageResId();
        db.execSQL(sql);
        updatePageNotMark(comicData);
    }

    /**
     * 将页面改为是书签的状态
     * @param comicData
     */
    public void updatePageMark(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update pages set isBookmark = 1 where imageResId = "+comicData.getImageResId();
        db.execSQL(sql);
    }
    /**
     * 将页面改为不是书签的状态
     * @param comicData
     */
    public void updatePageNotMark(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update pages set isBookmark = 0 where imageResId = "+comicData.getImageResId();
        db.execSQL(sql);
    }
    /**
     * 将页面改为是收藏的状态
     * @param comicData
     */
    public void updatePageCollect(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update pages set isCollection = 1 where imageResId = "+comicData.getImageResId();
        db.execSQL(sql);
    }
    /**
     * 将页面改为不是收藏的状态
     * @param comicData
     */
    public void updatePageNotCollect(ComicData comicData){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update pages set isCollection = 0 where imageResId = "+comicData.getImageResId();
        db.execSQL(sql);
    }

    public List<ComicData> findAllCollection(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ComicData> pages = new ArrayList<ComicData>();
        String sql = "select * from pages where isCollection=1";
        Cursor cursor = db.rawQuery(sql,null);
        int resultCounts = cursor.getCount();
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
            comicData.setCollection(cursor.getInt(5));
            comicData.setBookmark(cursor.getInt(6));
            pages.add(comicData);
            cursor.moveToNext();
        }
        return pages;


    }

}
