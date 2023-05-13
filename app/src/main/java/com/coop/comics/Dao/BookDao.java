package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coop.comics.Model.Book;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.ArrayList;

public class BookDao extends SQLiteOpenHelper {

    public BookDao(Context context) {
        super(context, "comics.db", null, 1);
    }
    ArrayList<ComicData> pages = new ArrayList<ComicData>();
    public void initBook(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1,"战火中的青春",R.mipmap.a01_index));
        bookList.add(new Book(2,"上甘岭",R.mipmap.a02_index));
        bookList.add(new Book(3,"百合花",R.mipmap.a0301));
        bookList.add(new Book(4,"平民的故事",R.mipmap.a0401));

        initBook_one();  //初始化第一本书
        initBook_two();  //初始化第二本书

        for (int i = 0; i < bookList.size(); i++) {
            ContentValues book_newValues = new ContentValues();  //存放书籍内容
            book_newValues.put("book_name",bookList.get(i).getName());
            book_newValues.put("book_photo",bookList.get(i).getPhoto());
            db.insert("book",null,book_newValues);
        }

        for (int i = 0; i < pages.size(); i++) {
            ContentValues pageValues = new ContentValues();   //存放页面内容
            pageValues.put("imageResId",pages.get(i).getImageResId());
            pageValues.put("bookId",pages.get(i).getBookId());
            pageValues.put("title",pages.get(i).getTitle());
            pageValues.put("summary",pages.get(i).getSummary());
            pageValues.put("page",pages.get(i).getPage());
            pageValues.put("isCollection",pages.get(i).isCollection());
            pageValues.put("isBookmark",pages.get(i).isBookmark());
            db.insert("pages",null,pageValues);
        }
    }
    public ArrayList<Book> queryBooks(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Book> books = new ArrayList<Book>();
        //查询所有book
        Cursor results = db.query("book",null,null,null,null,null,null);
        int resultCounts = results.getCount();

        if(resultCounts==0||!results.moveToFirst()){
            return books;
        }

        for(int i=0;i<resultCounts;i++){
            @SuppressLint("Range") Book book = new Book(i+1,results.getString(results.getColumnIndex("book_name")),results.getInt(results.getColumnIndex("book_photo")) );
            books.add(book);
            results.moveToNext();
        }
        return books;
    }

    public void initBook_one(){
        pages.add(new ComicData(R.mipmap.a01_cover,1,"封面页","封面",-1));
        pages.add(new ComicData(R.mipmap.a01_breif,1,"简介页","简介内容",0));
        pages.add(new ComicData(R.mipmap.a0101,1,"第一幕",
                "1946年冬天，国民党反动派向老根据地进攻，某县区小队被敌人包图在山头上。群多同志都相朱牺性了,就剩下一个名叫高山的区小队长，对付着山下的敌人。"
                ,1));
        pages.add(new ComicData(R.mipmap.a0102,1,"第二幕",
                "大批敌人嗥叫着冲上来，高山愤怒地跳到岩石上，操起机榆向敌人射击。"
                ,2));
        pages.add(new ComicData(R.mipmap.a0103,1,"第三幕",
                "机枪没子弹了,背后山脚下又喊起了杀声,敌人包围上来了。"
                ,3));
        pages.add(new ComicData(R.mipmap.a0104,1,"第四幕",
                "高山猛然跳起把机枪砸碎,拾起一支步枪继续向敌人射击。"
                ,4));
    }
    public void initBook_two(){
        pages.add(new ComicData(R.mipmap.a02_cover,2,"封面页",
                "封面内容"
                ,-1));
        pages.add(new ComicData(R.mipmap.a02_breif,2,"简介页",
                "简介内容"
                ,0));
        pages.add(new ComicData(R.mipmap.a0201,2,"第一幕",
                "1952年秋天的清晨,驻守上甘岭主睾陕地的一速速长張文贵接到通知,说师长即来这里视察。他走出坑道，到山士幻那边去迎接师长。"
                ,1));
        pages.add(new ComicData(R.mipmap.a0202,2,"第二幕",
                "他沿着交通沟往前走了一程,就在一个拐弯处,碰上了师长崔信僳。"
                ,2));
        pages.add(new ComicData(R.mipmap.a0203,2,"第三幕",
                "張文贵陪着师长视察了坑道,然后来到观察所。师长向前沿障地看了几分钟光景,就地坐在一块石头上,周道:“張文贵，你知道我今天来这里的用意么?”"
                ,3));
        pages.add(new ComicData(R.mipmap.a0204,2,"第四幕",
                "(4）师长接着又说，最近以来，敌人整师整师的往这一带钢动兵力，范佛里特、李承晚亲自来看地形，他们准备了几个月，看情形就要向这里进攻。"
                ,4));
    }
    public void initBook_three(){

    }
    public void initBook_four(){

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
