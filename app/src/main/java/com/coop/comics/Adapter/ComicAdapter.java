/**
 * @ClassName ComicAdapter
 * @Author 24
 * @Date 2023/5/10 14:34
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Adapter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.coop.comics.Activity.ComicActivity;
import com.coop.comics.Fragment.ComicFragment;
import com.coop.comics.Model.ComicData;

import java.util.List;

public class ComicAdapter extends FragmentStatePagerAdapter {
    
    private List<ComicData> comicData;
    private int textSizeIndex;
    private ComicActivity activity;
    
    public ComicAdapter(FragmentManager fm, List<ComicData> comicData, int textSizeIndex, ComicActivity activity) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.comicData = comicData;
        this.textSizeIndex = textSizeIndex;
        this.activity = activity;
    }
    
    public List<ComicData> getComicData() {
        return comicData;
    }
    
    public void setComicData(List<ComicData> comicData) {
        this.comicData = comicData;
    }
    
    public int getTextSizeIndex() {
        return textSizeIndex;
    }
    
    public void setTextSizeIndex(int textSizeIndex) {
        this.textSizeIndex = textSizeIndex;
    }
    
    public ComicActivity getActivity() {
        return activity;
    }
    
    public void setActivity(ComicActivity activity) {
        this.activity = activity;
    }
    
    @Override
    public Fragment getItem(int position) {
        ComicFragment fragment = new ComicFragment();
        Bundle bundle = new Bundle();
        
        bundle.putSerializable("comicData", comicData.get(position));   // 获取数据类
        bundle.putInt("sumPages", comicData.size() - 2);    // 获取总页数
        bundle.putInt("textSizeIndex", textSizeIndex);  // 获取字体大小索引
        
        fragment.setArguments(bundle);
        fragment.setButtonClickListener(activity);
        
        return fragment;

    }
    
    @Override
    public int getCount() {
        return comicData.size();
    }
}

//    may the force be with you.
//    @ClassName   ComicAdapter
//    Created by 24 on 2023/5/10.
