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

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SQLiteDatabase db;
    private String goWhere;
    
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
        
        goWhere = getIntent().getStringExtra("goWhere");  // 获取跳转的 Fragment

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        
        // 初始化 ViewPager 和 TabLayout
        initViewPagerAndTabs();
    }
    
    private void initViewPagerAndTabs() {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {   // 创建 FragmentPagerAdapter
            
            @NonNull
            @Override
            public Fragment getItem(int position) {
                
                if (Objects.equals(goWhere, "collection")) {    // 如果去收藏Fragment
                    return new CollectionFragment();
                } else if (Objects.equals(goWhere, "bookmark")) {   // 如果去书签Fragment
                    return new BookmarkFragment();
                } else {
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
        
        // 根据 goWhere 设置当前选中的标签页
        if (Objects.equals(goWhere, "collection")) {
            viewPager.setCurrentItem(1);
        } else if (Objects.equals(goWhere, "bookmark")) {
            viewPager.setCurrentItem(2);
        }
        
        goWhere = null; // 解决利用goWhere跳转后其它Fragment不能正常显示的问题
    }
    
}
