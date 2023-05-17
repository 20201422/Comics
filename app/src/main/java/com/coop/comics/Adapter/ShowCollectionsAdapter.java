package com.coop.comics.Adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.coop.comics.Activity.CollectionActivity;
import com.coop.comics.Fragment.ComicFragment;
import com.coop.comics.Model.ComicData;

import java.util.List;

public class ShowCollectionsAdapter extends FragmentStatePagerAdapter {

    private List<ComicData> comicData;
    private int textSizeIndex;
    private CollectionActivity activity;

    public ShowCollectionsAdapter(FragmentManager fm, List<ComicData> comicData, int textSizeIndex, CollectionActivity activity) {
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

    public CollectionActivity getActivity() {
        return activity;
    }

    public void setActivity(CollectionActivity activity) {
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        ComicFragment fragment = new ComicFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("comicData", comicData.get(position));   // 获取数据类
        bundle.putInt("sumPages", comicData.size());    // 获取总页数
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
