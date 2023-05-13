package com.coop.comics.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.coop.comics.Adapter.ComicAdapter;
import com.coop.comics.Dao.PagesDao;
import com.coop.comics.Model.Bookmark;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.ArrayList;
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

        List<ComicData> comicData = new ArrayList<>();

        comicData = pagesDao.queryByBookId(bookId);   //根据bookId查询所有页面内容
//        comicData.add(new ComicData(R.mipmap.whwojx, "三体", "刘慈欣 著", -1));
//        comicData.add(new ComicData(R.mipmap.whwojx, "简介",
//                "宇宙就是一座黑暗森林，每个文明都是带枪的猎人，像幽灵般潜行于林间，轻轻拨开挡路的树枝，竭力不让脚步发出一点儿声音，" +
//                        "连呼吸都必须小心翼翼：他必须小心，因为林中到处都有与他一样潜行的猎人，如果他发现了别的生命，能做的只有一件事：" +
//                        "开枪消灭。在这片森林中，他人就是地狱，就是永恒的威胁，任何暴露自己存在的生命都将很快被消灭，这就是宇宙文明的图景，" +
//                        "这就是对费米悖论的一种解释。", 0));
//        comicData.add(new ComicData(R.mipmap.whwojx, "故事标题1", "故事简介1", 1));
//        comicData.add(new ComicData(R.mipmap.whwojx, "故事标题2", "故事简介2", 2));
//        comicData.add(new ComicData(R.mipmap.whwojx, "故事标题3", "故事简介3", 3));

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
