package com.coop.comics.Activity;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.coop.comics.ComicAdapter;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.ArrayList;
import java.util.List;

public class ComicActivity extends AppCompatActivity {
    
    private ViewPager viewPager;
    private ComicAdapter adapter;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);
        
        viewPager = findViewById(R.id.view_pager);
        adapter = new ComicAdapter(getSupportFragmentManager(), getComicData());
        viewPager.setAdapter(adapter);
    }
    
    private List<ComicData> getComicData() {
        List<ComicData> comicData = new ArrayList<>();
        comicData.add(new ComicData(R.drawable.whwojx, "故事标题1", "故事简介1"));
        comicData.add(new ComicData(R.drawable.whwojx, "故事标题2", "故事简介2"));
        comicData.add(new ComicData(R.drawable.whwojx, "故事标题3", "故事简介3"));
        // ... 添加更多故事
        return comicData;
    }
    
}
