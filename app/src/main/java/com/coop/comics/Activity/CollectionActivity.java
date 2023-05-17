package com.coop.comics.Activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.coop.comics.Adapter.ShowCollectionsAdapter;
import com.coop.comics.Dao.PagesDao;
import com.coop.comics.Fragment.ComicFragment;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.List;

public class CollectionActivity extends AppCompatActivity implements ComicFragment.ComicFragmentButtonClickListener {

    private ViewPager viewPager;
    private ShowCollectionsAdapter adapter;
    private int bookId;
    private int stopId;
    private static final int AUTO_SCROLL_DELAY = 24; // 延迟时间，单位为毫秒
    private Handler autoScrollHandler;
    private Runnable autoScrollRunnable;
    private String[] textSizeButtonText = {"小", "中", "大"};
    private int textSizeIndex = 1; // 默认字体大小的索引值为1
    private Button changeTextSizeButton;

    public int getTextSizeIndex() {
        return textSizeIndex;
    }

    public void setTextSizeIndex(int textSizeIndex) {
        this.textSizeIndex = textSizeIndex;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        // 接收bookId
        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId", 0);   // 接收到的是int型的bookId，当没接收到默认bookId为0
        stopId = intent.getIntExtra("stopPage", -1);  // 接收传来的书签页数, 没接收到就默认是-1

        adapter = new ShowCollectionsAdapter(getSupportFragmentManager(), getComicData(), textSizeIndex, this);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
    }

    private List<ComicData> getComicData() {
        PagesDao pagesDao = new PagesDao(this);

        List<ComicData> comicData;

        comicData = pagesDao.findAllCollection();

        System.out.println(comicData.size());

        return comicData;
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAutoScroll();
    }

    private void startAutoScroll() {    // 自动轮播
        autoScrollHandler = new Handler(Looper.getMainLooper());
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int nextItem = currentItem + 1;

                if (nextItem >= adapter.getCount()) {
                    nextItem = 0; // 循环到第一个项
                }

                if (stopId != -1) {   // 没有书签，不轮播
                    viewPager.setCurrentItem(nextItem, true);
                }

                if (currentItem == stopId) {   // 如果到达书签页
                    stopAutoScroll();   // 停止轮播
                } else {
                    autoScrollHandler.postDelayed(this, AUTO_SCROLL_DELAY);
               }
            }
        };

        autoScrollHandler.postDelayed(autoScrollRunnable, AUTO_SCROLL_DELAY);
    }

    public void stopAutoScroll() {  // 停止轮播
        if (autoScrollHandler != null && autoScrollRunnable != null) {
            autoScrollHandler.removeCallbacks(autoScrollRunnable);
        }
    }

    private void changeTextSize() {
        textSizeIndex++;

        if (textSizeIndex == textSizeButtonText.length) {
            textSizeIndex = 0;
        }
        adapter.setTextSizeIndex(textSizeIndex);    // 将改变的大小索引传给适配器

        // 遍历所有 Fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof ComicFragment) {
                // 获取对应 Fragment 的文本视图引用
                ComicFragment myFragment = (ComicFragment) fragment;

                TextView titleView = myFragment.getTitleView(); // 获取标题组件
                TextView summaryView = myFragment.getSummaryView(); // 获取内容组件
                TextView pageView = myFragment.getPageView();   // 获取页数组件
                Button textSizeButton = myFragment.getTextSizeButton();

                // 更改字体大小
                if (myFragment.getPage() >= 0) {
                    titleView.setTextSize(myFragment.getTitleTextSize()[textSizeIndex]);    // 改变标题大小
                    summaryView.setTextSize(myFragment.getSummaryTextSize()[textSizeIndex]);    // 改变内容大小
                    pageView.setTextSize(myFragment.getPageTextSize()[textSizeIndex]);  // 改变页数大小
                    textSizeButton.setText(myFragment.getTextSizeButtonText()[textSizeIndex]);  // 改变字体大小按钮的字体
                }
            }
        }
    }

    @Override
    public void onTextSizeButtonClick() {
        changeTextSize();
    }
}
