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
import com.coop.comics.R;

import java.util.ArrayList;

public class BookDao extends SQLiteOpenHelper {

    public BookDao(Context context) {
        super(context, "comics.db", null, 1);
    }

    public void initBook(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1,"战火中的青春",R.mipmap.a0101));
        bookList.add(new Book(2,"刘胡兰",R.mipmap.a0201));
        bookList.add(new Book(3,"百合花",R.mipmap.a0301));
        bookList.add(new Book(4,"平民的故事",R.mipmap.a0401));

        ArrayList<ComicData> pages = new ArrayList<ComicData>();
        pages.add(new ComicData(R.mipmap.a0102,1,"封面页","封面",-1));
        pages.add(new ComicData(R.mipmap.a0103,1,"简介页","简介内容",0));
        pages.add(new ComicData(R.mipmap.a0104,1,"第一幕","第一页内容",1));

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

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
