/**
 * @ClassName ComicData
 * @Author 24
 * @Date 2023/5/10 14:32
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Model;

public class ComicData {
    
    private int imageResId;
    private String title;
    private String summary;
    
    public ComicData() {
    }
    
    public ComicData(int imageResId, String title, String summary) {
        this.imageResId = imageResId;
        this.title = title;
        this.summary = summary;
    }
    
    public int getImageResId() {
        return imageResId;
    }
    
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
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
}

//    may the force be with you.
//    @ClassName   ComicData
//    Created by 24 on 2023/5/10.
