package com.coop.comics.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.coop.comics.Dao.BookDao;
import com.coop.comics.Dao.CreateDB;
import com.coop.comics.Fragment.BookmarkFragment;
import com.coop.comics.Fragment.CollectionFragment;
import com.coop.comics.Fragment.HomeFragment;
import com.coop.comics.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SQLiteDatabase db;
    
    public MainActivity() {
    }
    
    public MainActivity(ViewPager viewPager, TabLayout tabLayout) {
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }
    
    public ViewPager getViewPager() {
        return viewPager;
    }
    
    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }
    
    public TabLayout getTabLayout() {
        return tabLayout;
    }
    
    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new CreateDB(this).getDb();
        BookDao bookDao = new BookDao();
        Cursor results = db.query("book",null,null,null,null,null,null);
        if(results.getCount()==0)
            db = bookDao.initBook(db);   //初始化数据库

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        
        // 初始化 ViewPager 和 TabLayout
        initViewPagerAndTabs();
    }
    
    private void initViewPagerAndTabs() {
        // 创建 FragmentPagerAdapter
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            
            @NonNull
            @Override
            public Fragment getItem(int position) {
                // 根据位置获取对应的 Fragment
                switch (position) {
                    case 1:
                        return new CollectionFragment();
                    case 2:
                        return new BookmarkFragment();
                    case 0:
                    default:
                        return new HomeFragment(db);


                }
            }
            
            @Override
            public int getCount() { // 返回 Fragment 的数量
                
                return 3;
            }
            
            @Override
            public CharSequence getPageTitle(int position) {    // 返回 TabLayout 中对应位置的标题
                
                switch (position) {
                    case 1:
                        return "收藏";
                    case 2:
                        return "书签";
                    case 0:
                    default:
                        return "主页";
                }
            }
        };
        
        
        viewPager.setAdapter(adapter);  // 设置 ViewPager 的适配器
        
        
        tabLayout.setupWithViewPager(viewPager);    // 将 TabLayout 和 ViewPager 关联起来
    }
    
}
