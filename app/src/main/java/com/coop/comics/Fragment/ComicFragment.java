/**
 * @ClassName ComicFragment
 * @Author 24
 * @Date 2023/5/10 14:36
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.coop.comics.R;

public class ComicFragment extends Fragment {

    private int imageResId;
    private String title;
    private String summary;
    private int page;
    private int sumPages;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);
        ImageView imageView = view.findViewById(R.id.image_view);   // 获取图片组件
        TextView titleView = view.findViewById(R.id.title_view);    // 获取标题组件
        TextView summaryView = view.findViewById(R.id.summary_view);    // 获取内容组件
        TextView pageView = view.findViewById(R.id.page_view);  // 获取页数组件
        Button collectionButton = view.findViewById(R.id.collection_button);    // 获取收藏组件
        Button bookmarkButton = view.findViewById(R.id.bookmark_button);    // 获取书签组件

        Bundle args = getArguments();   // 接收数据
        if (args != null) {
            imageResId = args.getInt("imageResId"); // 获取图片资源
            title = args.getString("title");    // 获取标题
            summary = args.getString("summary");    // 获取内容
            page = args.getInt("page"); // 获取页数
            sumPages = args.getInt("sumPages"); // 获取总页数
        }

        if (page == -1) {   // 是封面页
            ViewGroup.LayoutParams layoutParamsForImage = imageView.getLayoutParams();  // 获取当前 ImageView 的布局参数
            layoutParamsForImage.height = 1240;    // 设置高度值
            imageView.setLayoutParams(layoutParamsForImage);    // 应用新的布局参数

            titleView.setTextSize(42);  // 放大标题字体

            ViewGroup.MarginLayoutParams layoutParamsForSummary = (ViewGroup.MarginLayoutParams)
                    summaryView.getLayoutParams(); // 获取当前 TextView 的布局参数
            layoutParamsForSummary.topMargin = -90;   // 设置上边距值
            summaryView.setLayoutParams(layoutParamsForSummary);    // 应用新的布局参数
            summaryView.setTextSize(32);    // 放大作者字体
        } else if (page == 0) { // 是简介页
            // 待扩展
        } else {    // 是正文页
            pageView.setVisibility(View.VISIBLE);   // 页数可见
            pageView.setText("第 " + page + " 页/共 "+ sumPages + " 页");
            
            collectionButton.setVisibility(View.VISIBLE);
            bookmarkButton.setVisibility(View.VISIBLE);
        }

        if (summary != null && !summary.equals("") && page >= 0) {  // 如果有内容并且不是标题页
            summaryView.setBackgroundResource(R.drawable.border_radius);    // 设置背景
        }

        imageView.setImageResource(imageResId); // 添加图片到组件
        titleView.setText(title);   // 添加标题到组件
        summaryView.setText(summary);   // 添加内容到组件

        return view;
    }

}

//    may the force be with you.
//    @ClassName   ComicFragment
//    Created by 24 on 2023/5/10.
