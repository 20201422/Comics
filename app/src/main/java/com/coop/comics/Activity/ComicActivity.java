package com.coop.comics.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.coop.comics.Adapter.ComicAdapter;
import com.coop.comics.Dao.PagesDao;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.List;

public class ComicActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ComicAdapter adapter;
    private int bookId;
    private int stopPage;
    private static final int AUTO_SCROLL_DELAY = 24; // 延迟时间，单位为毫秒
    private Handler autoScrollHandler;
    private Runnable autoScrollRunnable;
    
    public ComicActivity() {
    }
    
    public ComicActivity(ViewPager viewPager, ComicAdapter adapter, int bookId, int stopPage,
                         Handler autoScrollHandler, Runnable autoScrollRunnable) {
        this.viewPager = viewPager;
        this.adapter = adapter;
        this.bookId = bookId;
        this.stopPage = stopPage;
        this.autoScrollHandler = autoScrollHandler;
        this.autoScrollRunnable = autoScrollRunnable;
    }
    
    public ViewPager getViewPager() {
        return viewPager;
    }
    
    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }
    
    public ComicAdapter getAdapter() {
        return adapter;
    }
    
    public void setAdapter(ComicAdapter adapter) {
        this.adapter = adapter;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public int getStopPage() {
        return stopPage;
    }
    
    public void setStopPage(int stopPage) {
        this.stopPage = stopPage;
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
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        // 接收bookId
        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId", 0);   // 接收到的是int型的bookId，当没接收到默认bookId为0
        stopPage = intent.getIntExtra("stopPage", -1);  // 接收传来的书签页数, 没接收到就默认是-1
        
        viewPager = findViewById(R.id.view_pager);
        
        adapter = new ComicAdapter(getSupportFragmentManager(), getComicData());
        
        viewPager.setAdapter(adapter);
    }

    private List<ComicData> getComicData() {
        PagesDao pagesDao = new PagesDao(this);
        List<ComicData> comicData;
        comicData = pagesDao.queryByBookId(bookId);   //根据bookId查询所有页面内容

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
                
                if (stopPage != -1) {   // 没有书签，不轮播
                    viewPager.setCurrentItem(nextItem, true);
                }
                
                if (currentItem == stopPage) {   // 如果到达书签页
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

}
