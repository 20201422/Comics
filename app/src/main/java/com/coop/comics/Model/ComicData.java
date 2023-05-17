/**
 * @ClassName ComicData
 * @Author 24
 * @Date 2023/5/10 14:32
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

import java.io.Serializable;

public class ComicData implements Serializable {
    
    private int imageResId;
    private int bookId;
    private String bookName;
    private String title;
    private String summary;
    private int page;
    private int isCollection;
    private int isBookmark;
    
    public ComicData() {
    }

    public ComicData(int imageResId, int bookId, String title, String summary, int page) {
        this.imageResId = imageResId;
        this.bookId = bookId;
        this.title = title;
        this.summary = summary;
        this.page = page;
        this.isBookmark=0;
        this.isCollection=0;
    }

    public int getImageResId() {
        return imageResId;
    }

    
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int isCollection() {
        return isCollection;
    }
    
    public void setCollection(int collection) {
        isCollection = collection;
    }
    
    public int isBookmark() {
        return isBookmark;
    }
    
    public void setBookmark(int bookmark) {
        isBookmark = bookmark;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}

//    may the force be with you.
//    @ClassName   ComicData
//    Created by 24 on 2023/5/10.
