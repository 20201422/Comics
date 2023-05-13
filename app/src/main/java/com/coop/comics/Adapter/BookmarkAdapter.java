/**
 * @ClassName BookmarkAdapter
 * @Author 24
 * @Date 2023/5/12 20:50
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.coop.comics.Model.Book;
import com.coop.comics.Model.Bookmark;
import com.coop.comics.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class BookmarkAdapter extends BaseAdapter {
    
    private List<Bookmark> bookmarks;
    private Context context;
    private LayoutInflater inflater;
    
    public BookmarkAdapter() {
    }
    
    public BookmarkAdapter(List<Bookmark> bookmarks, Context context) {
        this.bookmarks = bookmarks;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    
    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }
    
    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
    
    public Context getContext() {
        return context;
    }
    
    public void setContext(Context context) {
        this.context = context;
    }
    
    public LayoutInflater getInflater() {
        return inflater;
    }
    
    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }
    
    @Override
    public int getCount() {
        return bookmarks.size();
    }
    
    @Override
    public Object getItem(int i) {
        return null;
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        
        BookmarkAdapter.ViewHolder viewHolder;
        
        if (view == null) {  // 没有才创建
            view = inflater.inflate(R.layout.bookmark_list, null);
            
            viewHolder = new BookmarkAdapter.ViewHolder(); // new一个
            
            // 通过id绑定组件
            viewHolder.bookmark1 = view.findViewById(R.id.bookmark_1);
            viewHolder.bookmark2 = view.findViewById(R.id.bookmark_2);
            viewHolder.bookmark3 = view.findViewById(R.id.bookmark_3);
            
            view.setTag(viewHolder);
        } else {
            viewHolder = (BookmarkAdapter.ViewHolder) view.getTag();
        }
        
        // 设置对应属性
        viewHolder.bookmark1.setText(String.valueOf(i + 1));
        viewHolder.bookmark2.setText(bookmarks.get(i).getBookName());
        viewHolder.bookmark3.setText("第 " + bookmarks.get(i).getPage() + " 幕");
        
        return view;
    }
    
    private static final class ViewHolder {
        
        private TextView bookmark1;
        private TextView bookmark2;
        private TextView bookmark3;
        
    }
    
}

//    may the force be with you.
//    @ClassName   BookmarkAdapter
//    Created by 24 on 2023/5/12.
