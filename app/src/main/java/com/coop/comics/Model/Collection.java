/**
 * @ClassName Collection
 * @Author 24
 * @Date 2023/5/12 19:32
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

public class Collection {
    private int imageResId; //图片
    
    private int collectionId;
    private int bookId;
    private String bookName;
    private int page;

    
    public Collection() {
    }

    public Collection(int imageResId, int collectionId, int bookId, String bookName, int page) {
        this.imageResId = imageResId;
        this.collectionId = collectionId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.page = page;
    }

    public int getCollectionId() {
        return collectionId;
    }
    
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
}

//    may the force be with you.
//    @ClassName   Collection
//    Created by 24 on 2023/5/12.
