package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coop.comics.Model.Collection;
import com.coop.comics.Model.ComicData;

import java.util.ArrayList;

public class CollectionDao extends SQLiteOpenHelper {
    public CollectionDao(Context context) {
        super(context, "comics.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 查询所有收藏
     * @return
     */
    public ArrayList<Collection> queryAllCollection(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Collection> list = new ArrayList<Collection>();

        Cursor results = db.query("collection",null,null,null,null,null,null);
        int resultCounts = results.getCount();

        if(resultCounts==0||!results.moveToFirst()){
            return list;
        }

        for(int i=0;i<resultCounts;i++){
            @SuppressLint("Range")
            Collection collection = new Collection(results.getInt(0),results.getInt(1),results.getInt(2),results.getString(3),results.getInt(4));
            list.add(collection);
            results.moveToNext();
        }

        return list;
    }

    /**
     * 根据收藏id查询某一页内容
     * @param id
     * @return
     */
    public ComicData findPageByCollectionId(int id){
        SQLiteDatabase db = getWritableDatabase();
        ComicData comicData  = null;
        String sql = "select * from pages where imageResId = "+id;
        Cursor cursor = db.rawQuery(sql,null);  //获取对应的书
        int resultCounts = cursor.getCount();
        if(resultCounts==0||!cursor.moveToFirst()){
            return comicData;
        }
        comicData = new ComicData(cursor.getInt(0),cursor.getInt(1), cursor.getString(2),
                cursor.getString(3),cursor.getInt(4));
        comicData.setCollection(cursor.getInt(5));
        comicData.setBookmark(cursor.getInt(6));
        return comicData;
    }

    /**
     * 删除某个收藏
     */
    public void delCollection(){

    }
}
