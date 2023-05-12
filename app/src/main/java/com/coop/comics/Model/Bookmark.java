/**
 * @ClassName Bookmark
 * @Author 24
 * @Date 2023/5/12 19:29
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

public class Bookmark {
    private int bookmarkId;
    private int bookId;
    private int page;
    
    public Bookmark() {
    }
    
    public Bookmark(int bookmarkId, int bookId, int page) {
        this.bookmarkId = bookmarkId;
        this.bookId = bookId;
        this.page = page;
    }
    
    public int getBookmarkId() {
        return bookmarkId;
    }
    
    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
}

//    may the force be with you.
//    @ClassName   Bookmark
//    Created by 24 on 2023/5/12.
