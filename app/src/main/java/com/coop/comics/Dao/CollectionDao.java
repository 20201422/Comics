package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coop.comics.Model.Bookmark;
import com.coop.comics.Model.Collection;
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
            Collection collection = new Collection(results.getInt(0),results.getInt(1),results.getString(2),results.getInt(3));
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
    public Collection findPageByCollectionId(int id){

        return null;
    }

    /**
     * 删除某个收藏
     */
    public void delCollection(){

    }
}
