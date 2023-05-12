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
import com.coop.comics.Fragment.ComicFragment;
import com.coop.comics.Model.ComicData;

import java.util.List;

public class ComicAdapter extends FragmentStatePagerAdapter {
    
    private List<ComicData> comicData;
    
    public ComicAdapter(FragmentManager fm, List<ComicData> comicData) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.comicData = comicData;
    }
    
    @Override
    public Fragment getItem(int position) {
        ComicFragment fragment = new ComicFragment();
        Bundle args = new Bundle();
        
        args.putInt("imageResId", comicData.get(position).getImageResId()); // 获取图像并利用Bundle传送
        args.putString("title", comicData.get(position).getTitle());    // 获取标题并利用Bundle传送
        args.putString("summary", comicData.get(position).getSummary());    // 获取内容并利用Bundle传送
        args.putInt("page", comicData.get(position).getPage()); // 获取页数并利用Bundle传送
        args.putInt("sumPages", getCount() - 2);
        fragment.setArguments(args);
        
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