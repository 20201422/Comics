/**
 * @ClassName Collection
 * @Author 24
 * @Date 2023/5/12 19:32
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

public class Collection {
    
    private int collectionId;
    private int bookId;
    private int page;
    
    public Collection() {
    }
    
    public Collection(int collectionId, int bookId, int page) {
        this.collectionId = collectionId;
        this.bookId = bookId;
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
