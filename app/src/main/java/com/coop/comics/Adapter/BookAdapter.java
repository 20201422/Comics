/**
 * @ClassName MyAdapter
 * @Author 24
 * @Date 2023/4/25 16:31
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.coop.comics.Model.Book;
import com.coop.comics.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    
    private List<Book> books;
    private Context context;
    private LayoutInflater inflater;
    
    public BookAdapter() {
    }
    
    public BookAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
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
        return books.size();
    }
    
    @Override
    public Object getItem(int i) {
        return null;
    }
    
    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        
        ViewHolder viewHolder;
        
        if (view == null) {  // 没有才创建
            view = inflater.inflate(R.layout.book_list, null);
            
            viewHolder = new ViewHolder(); // new一个
            
            // 通过id绑定组件
            viewHolder.bookName = view.findViewById(R.id.book_name);
            viewHolder.bookPhoto = view.findViewById(R.id.book_image);
            
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        
        // 设置对应属性
        viewHolder.bookName.setText(books.get(i).getName());
        viewHolder.bookPhoto.setImageResource(books.get(i).getPhoto());
        
        return view;
    }
    
    private static final class ViewHolder {
        
        private TextView bookName;
        private RoundedImageView bookPhoto;
        
    }
    
}

//    may the force be with you.
//
//    Created by 24 on 2023/4/25.
