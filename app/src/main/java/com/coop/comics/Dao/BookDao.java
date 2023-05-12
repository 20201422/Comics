package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coop.comics.Model.Book;
import com.coop.comics.R;

import java.util.ArrayList;

public class BookDao {

    public SQLiteDatabase initBook(SQLiteDatabase db){
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1,"战火中的青春",R.mipmap.a0101));
        bookList.add(new Book(2,"战火中的青春",R.mipmap.a0201));
        bookList.add(new Book(3,"战火中的青春",R.mipmap.a0301));
        bookList.add(new Book(4,"战火中的青春",R.mipmap.a0401));
        for (int i = 0; i < bookList.size(); i++) {
            ContentValues book_newValues = new ContentValues();
            book_newValues.put("book_name",bookList.get(i).getName());
            book_newValues.put("book_photo",bookList.get(i).getPhoto());
            db.insert("book",null,book_newValues);
        }
        return db;
    }
    public ArrayList<Book> queryBooks(SQLiteDatabase db){

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
}
