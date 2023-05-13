/**
 * @ClassName ComicAdapter
 * @Author 24
 * @Date 2023/5/10 14:34
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.coop.comics.Fragment.ComicFragment;
import com.coop.comics.Model.Bookmark;
import com.coop.comics.Model.ComicData;

import java.util.List;

public class ComicAdapter extends FragmentStatePagerAdapter {
    
    private List<ComicData> comicData;
    
    public ComicAdapter (FragmentManager fm, List<ComicData> comicData) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.comicData = comicData;
    }
    
    public List<ComicData> getComicData() {
        return comicData;
    }
    
    public void setComicData(List<ComicData> comicData) {
        this.comicData = comicData;
    }
    
    @Override
    public Fragment getItem(int position) {
        ComicFragment fragment = new ComicFragment();
        Bundle bundle = new Bundle();
        
        bundle.putSerializable("comicData", comicData.get(position));   // 获取数据类
        
        bundle.putInt("sumPages", comicData.size() - 2);

        fragment.setArguments(bundle);
        
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
