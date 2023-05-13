package com.coop.comics.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        ArrayList<Bookmark> list = new ArrayList<Bookmark>();



        return list;
    }

    /**
     * 根据书签id查询某一页内容
     * @param id
     * @return
     */
    public ComicData findPageByMarkId(int id){


        return null;
    }

    /**
     * 删除某个书签
     */
    public void delMark(){

    }

}
