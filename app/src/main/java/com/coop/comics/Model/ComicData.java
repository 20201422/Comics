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
    private String title;
    private String summary;
    private int page;
    private boolean isCollection;
    private boolean isBookmark;
    
    public ComicData() {
    }

    public ComicData(int imageResId, int bookId, String title, String summary, int page) {
        this.imageResId = imageResId;
        this.bookId = bookId;
        this.title = title;
        this.summary = summary;
        this.page = page;
    }

    public int getImageResId() {
        return imageResId;
    }
    
    public ComicData(int imageResId, String title, String summary, int page,
                     boolean isCollection, boolean isBookmark) {
        this.imageResId = imageResId;
        this.title = title;
        this.summary = summary;
        this.page = page;
        this.isCollection = isCollection;
        this.isBookmark = isBookmark;
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
    
    public boolean isCollection() {
        return isCollection;
    }
    
    public void setCollection(boolean collection) {
        isCollection = collection;
    }
    
    public boolean isBookmark() {
        return isBookmark;
    }
    
    public void setBookmark(boolean bookmark) {
        isBookmark = bookmark;
    }
}

//    may the force be with you.
//    @ClassName   ComicData
//    Created by 24 on 2023/5/10.
