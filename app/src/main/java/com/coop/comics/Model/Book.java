/**
 * @ClassName Book
 * @Author 24
 * @Date 2023/5/12 13:57
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

public class Book {
    
    private int id;
    private String name;
    private int photo;
    
    public Book() {
    }
    
    public Book(int id, String name, int photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPhoto() {
        return photo;
    }
    
    public void setPhoto(int photo) {
        this.photo = photo;
    }
}

//    may the force be with you.
//    @ClassName   Book
//    Created by 24 on 2023/5/12.
