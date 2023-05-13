/**
 * @ClassName ComicFragment
 * @Author 24
 * @Date 2023/5/10 14:36
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.coop.comics.Activity.ComicActivity;
import com.coop.comics.Activity.MainActivity;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.concurrent.ScheduledExecutorService;


public class ComicFragment extends Fragment {
    
    ImageView imageView;
    TextView titleView;
    TextView summaryView;
    TextView pageView;
    Button collectionButton;
    Button bookmarkButton;
    private ComicData comicData;
    private int sumPages;
    
    public ComicFragment() {
    }
    
    public ComicFragment(ImageView imageView, TextView titleView, TextView summaryView, TextView pageView,
                         Button collectionButton, Button bookmarkButton, ComicData comicData, int sumPages) {
        this.imageView = imageView;
        this.titleView = titleView;
        this.summaryView = summaryView;
        this.pageView = pageView;
        this.collectionButton = collectionButton;
        this.bookmarkButton = bookmarkButton;
        this.comicData = comicData;
        this.sumPages = sumPages;
    }
    
    public ImageView getImageView() {
        return imageView;
    }
    
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    
    public TextView getTitleView() {
        return titleView;
    }
    
    public void setTitleView(TextView titleView) {
        this.titleView = titleView;
    }
    
    public TextView getSummaryView() {
        return summaryView;
    }
    
    public void setSummaryView(TextView summaryView) {
        this.summaryView = summaryView;
    }
    
    public TextView getPageView() {
        return pageView;
    }
    
    public void setPageView(TextView pageView) {
        this.pageView = pageView;
    }
    
    public Button getCollectionButton() {
        return collectionButton;
    }
    
    public void setCollectionButton(Button collectionButton) {
        this.collectionButton = collectionButton;
    }
    
    public Button getBookmarkButton() {
        return bookmarkButton;
    }
    
    public void setBookmarkButton(Button bookmarkButton) {
        this.bookmarkButton = bookmarkButton;
    }
    
    public ComicData getComicData() {
        return comicData;
    }
    
    public void setComicData(ComicData comicData) {
        this.comicData = comicData;
    }
    
    public int getSumPages() {
        return sumPages;
    }
    
    public void setSumPages(int sumPages) {
        this.sumPages = sumPages;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);
        imageView = view.findViewById(R.id.image_view);   // 获取图片组件
        titleView = view.findViewById(R.id.title_view);    // 获取标题组件
        summaryView = view.findViewById(R.id.summary_view);    // 获取内容组件
        pageView = view.findViewById(R.id.page_view);  // 获取页数组件
        collectionButton = view.findViewById(R.id.collection_button);    // 获取收藏组件
        bookmarkButton = view.findViewById(R.id.bookmark_button);    // 获取书签组件

        Bundle bundle = getArguments();   // 接收数据
        if (bundle != null) {
            comicData = (ComicData) bundle.getSerializable("comicData");
            sumPages = bundle.getInt("sumPages"); // 获取总页数
        }

        modifyStyle();  // 修改样式以及嵌入数据
        
        collectionButton.setOnClickListener(v-> collectionClick()); // 点击收藏按钮
        bookmarkButton.setOnClickListener(v -> bookmarkClick());    // 点击书签按钮
        
        collectionButton.setOnLongClickListener(v -> {  // 长按收藏按钮跳转到收藏Fragment
           longCollectionClick();
            return true; // 事件已处理
        });
        bookmarkButton.setOnLongClickListener(v -> {    // 长安书签按钮跳转到书签Fragment
            longBookmarkClick();
            return true; // 事件已处理
        });

        return view;
    }
    
    @SuppressLint("SetTextI18n")
    public void modifyStyle() { // 界面处理
        if (comicData.getPage() == -1) {   // 是封面页
            ViewGroup.LayoutParams layoutParamsForImage = imageView.getLayoutParams();  // 获取当前 ImageView 的布局参数
            layoutParamsForImage.height = 1240;    // 设置高度值
            imageView.setLayoutParams(layoutParamsForImage);    // 应用新的布局参数
            
            titleView.setTextSize(42);  // 放大标题字体
            
            ViewGroup.MarginLayoutParams layoutParamsForSummary = (ViewGroup.MarginLayoutParams)
                    summaryView.getLayoutParams(); // 获取当前 TextView 的布局参数
            layoutParamsForSummary.topMargin = -90;   // 设置上边距值
            summaryView.setLayoutParams(layoutParamsForSummary);    // 应用新的布局参数
            summaryView.setTextSize(32);    // 放大作者字体
        } else if (comicData.getPage() == 0) { // 是简介页
            // 待扩展
        } else {    // 是正文页
            pageView.setVisibility(View.VISIBLE);   // 页数可见
            pageView.setText("第 " + comicData.getPage() + " 页/共 "+ sumPages + " 页");
            
            collectionButton.setVisibility(View.VISIBLE);   // 收藏按钮可见
            bookmarkButton.setVisibility(View.VISIBLE); // 书签按钮可见
        }
        
        if (comicData.getSummary() != null && !comicData.getSummary().equals("")
                && comicData.getPage() >= 0) {  // 如果有内容并且不是标题页
            summaryView.setBackgroundResource(R.drawable.border_radius);    // 设置背景
        }
        
        if (comicData.isCollection()) { // 如果已经是收藏的了 修改按钮样式
            collectionButton.setBackgroundResource(R.drawable.collection_round_button_background);
        }
        if (comicData.isBookmark()) {   // 如果已经有书签了 修改按钮样式
            bookmarkButton.setBackgroundResource(R.drawable.bookmark_round_button_background);
        }
        
        imageView.setImageResource(comicData.getImageResId()); // 添加图片到组件
        titleView.setText(comicData.getTitle());   // 添加标题到组件
        summaryView.setText(comicData.getSummary());   // 添加内容到组件
    }
    
    private void collectionClick() { // 收藏按钮
        if (comicData.isCollection()) {   // 已经在收藏了
            // 数据库操作删除收藏
            
            comicData.setCollection(false);
            collectionButton.setBackgroundResource(R.drawable.not_collection_round_button_background);  // 修改按钮样式
        } else {    // 还没有收藏
            // 数据库操作添加收藏
            
            comicData.setCollection(true);
            collectionButton.setBackgroundResource(R.drawable.collection_round_button_background);    // 修改按钮样式
        }
    }
    
    private void bookmarkClick() {   // 书签按钮
        if (comicData.isBookmark()) {   // 已经有书签了
            // 数据库操作删除书签
            
            comicData.setBookmark(false);
            bookmarkButton.setBackgroundResource(R.drawable.not_bookmark_round_button_background);  // 修改按钮样式
        } else {    // 还没有书签
            // 数据库操作添加书签
            
            comicData.setBookmark(true);
            bookmarkButton.setBackgroundResource(R.drawable.bookmark_round_button_background);  // 修改按钮样式
        }
    }
    
    private void longCollectionClick() {    // 长按收藏按钮
        Intent intent = new Intent(getActivity(), MainActivity.class);  // 创建跳转到目标 Activity 的 Intent
        intent.putExtra("goWhere", "collection");   // 去收藏Fragment
        startActivity(intent);  // 启动目标 Activity
    }
    
    private void longBookmarkClick() {  // 长按书签按钮
        Intent intent = new Intent(getActivity(), MainActivity.class);  // 创建跳转到目标 Activity 的 Intent
        intent.putExtra("goWhere", "bookmark"); // 去书签Fragment
        startActivity(intent);  // 启动目标 Activity
    }
    
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {   // 自动轮播
        super.setUserVisibleHint(isVisibleToUser);
        
        if (isVisibleToUser && getActivity() != null) {
            if (getActivity() instanceof ComicActivity) {
                ComicActivity comicActivity = (ComicActivity) getActivity();
                comicActivity.stopAutoScroll();
            }
        }
    }
    
}

//    may the force be with you.
//    @ClassName   ComicFragment
//    Created by 24 on 2023/5/10.
