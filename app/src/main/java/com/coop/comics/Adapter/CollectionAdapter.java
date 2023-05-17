package com.coop.comics.Adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coop.comics.Model.Collection;
import com.coop.comics.R;

import java.util.List;

public class CollectionAdapter extends BaseAdapter{
    private List<Collection> bookcollections;
    private Context context;
    private LayoutInflater inflater;

    public CollectionAdapter() {

    }
    public CollectionAdapter(List<Collection> bookcollections, Context context) {
        this.bookcollections = bookcollections;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<Collection> getBookcollections() {
        return bookcollections;
    }

    public void setBookcollections(List<Collection> collections) {
        this.bookcollections = collections;
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
        return bookcollections.size();
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

        ViewHolder viewHolder;

        if (view == null) {  // 没有才创建
            view = inflater.inflate(R.layout.bookmark_list, null);

            viewHolder = new ViewHolder(); // new一个

            // 通过id绑定组件
            viewHolder.collection1 = view.findViewById(R.id.bookmark_1);
            viewHolder.collection2 = view.findViewById(R.id.bookmark_2);
            viewHolder.collection3 = view.findViewById(R.id.bookmark_3);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 设置对应属性
        viewHolder.collection1.setText(String.valueOf(i + 1));
        viewHolder.collection2.setText(bookcollections.get(i).getBookName());
        viewHolder.collection3.setText("第 " + bookcollections.get(i).getPage() + " 幕");

        return view;
    }

    private static final class ViewHolder {

        private TextView collection1;
        private TextView collection2;
        private TextView collection3;

    }

}

/**
 * @ClassName ComicAdapter
 * @Author 24
 * @Date 2023/5/10 14:34
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/
