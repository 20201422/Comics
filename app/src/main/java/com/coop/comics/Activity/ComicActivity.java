package com.coop.comics.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.coop.comics.Adapter.ComicAdapter;
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

        // 接收bookId
        Intent intent = getIntent();
        int bookId = intent.getIntExtra("bookId", 0);   // 接收到的是int型的bookId，当没接收到默认bookId为0

        viewPager = findViewById(R.id.view_pager);
        adapter = new ComicAdapter(getSupportFragmentManager(), getComicData());
        viewPager.setAdapter(adapter);
    }

    private List<ComicData> getComicData() {
        List<ComicData> comicData = new ArrayList<>();
        comicData.add(new ComicData(R.mipmap.whwojx, "三体", "刘慈欣 著", -1));
        comicData.add(new ComicData(R.mipmap.whwojx, "简介",
                "  宇宙就是一座黑暗森林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，" +
                        "连呼吸都必须小心翼翼：他必须小心，因为林中到处都有与他一样潜行的猎人，如果他发现了别的生命，能做的只有一件事：" +
                        "开枪消灭。在这片森林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭，这就是宇宙文明的图景，" +
                        "这就是对费米悖论的一种解释。", 0));
        comicData.add(new ComicData(R.mipmap.whwojx, "故事标题1", "故事简介1", 1));
        comicData.add(new ComicData(R.mipmap.whwojx, "故事标题2", "故事简介2", 2));
        comicData.add(new ComicData(R.mipmap.whwojx, "故事标题3", "故事简介3", 3));
        // ... 添加更多故事
        return comicData;
    }

}
