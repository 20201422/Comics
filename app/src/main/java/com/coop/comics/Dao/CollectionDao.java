package com.coop.comics.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        ArrayList<Collection> list = new ArrayList<Collection>();


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
