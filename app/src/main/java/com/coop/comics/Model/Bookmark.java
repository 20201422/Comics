/**
 * @ClassName Bookmark
 * @Author 24
 * @Date 2023/5/12 19:29
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

import java.io.Serializable;

public class Bookmark implements Serializable {
    private int bookmarkId; // 书签id
    private String bookName;    // 书名称
    private int page;   // 页书
    
    public Bookmark() {
    }
    
    public Bookmark(int bookmarkId, String bookName, int page) {
        this.bookmarkId = bookmarkId;
        this.bookName = bookName;
        this.page = page;
    }
    
    public int getBookmarkId() {
        return bookmarkId;
    }
    
    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
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
//    @ClassName   Bookmark
//    Created by 24 on 2023/5/12.
