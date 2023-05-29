package com.coop.comics.Activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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
    
    public CollectionActivity() {
    }
    
    public CollectionActivity(ViewPager viewPager, ShowCollectionsAdapter adapter, int bookId, int stopId,
                              Handler autoScrollHandler, Runnable autoScrollRunnable,
                              String[] textSizeButtonText, int textSizeIndex) {
        this.viewPager = viewPager;
        this.adapter = adapter;
        this.bookId = bookId;
        this.stopId = stopId;
        this.autoScrollHandler = autoScrollHandler;
        this.autoScrollRunnable = autoScrollRunnable;
        this.textSizeButtonText = textSizeButtonText;
        this.textSizeIndex = textSizeIndex;
    }
    
    public ViewPager getViewPager() {
        return viewPager;
    }
    
    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }
    
    public ShowCollectionsAdapter getAdapter() {
        return adapter;
    }
    
    public void setAdapter(ShowCollectionsAdapter adapter) {
        this.adapter = adapter;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public int getStopId() {
        return stopId;
    }
    
    public void setStopId(int stopId) {
        this.stopId = stopId;
    }
    
    public Handler getAutoScrollHandler() {
        return autoScrollHandler;
    }
    
    public void setAutoScrollHandler(Handler autoScrollHandler) {
        this.autoScrollHandler = autoScrollHandler;
    }
    
    public Runnable getAutoScrollRunnable() {
        return autoScrollRunnable;
    }
    
    public void setAutoScrollRunnable(Runnable autoScrollRunnable) {
        this.autoScrollRunnable = autoScrollRunnable;
    }
    
    public String[] getTextSizeButtonText() {
        return textSizeButtonText;
    }
    
    public void setTextSizeButtonText(String[] textSizeButtonText) {
        this.textSizeButtonText = textSizeButtonText;
    }
    
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
                
                if (stopId == 1) {  // 解决收藏列表第一个不能停止的bug
                    stopAutoScroll();   // 停止轮播
                } else {
                    if (stopId != -1) {   // 第一页，不轮播
                        viewPager.setCurrentItem(nextItem, true);
                    }
                    
                    if (currentItem == stopId - 2) {   // 如果到达收藏页
                        stopAutoScroll();   // 停止轮播
                    } else {
                        autoScrollHandler.postDelayed(this, AUTO_SCROLL_DELAY);
                    }
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
