package com.coop.comics.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coop.comics.Adapter.BookAdapter;
import com.coop.comics.Dao.BookDao;
import com.coop.comics.Dao.CreateDB;
import com.coop.comics.Model.Book;
import com.coop.comics.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Book> books = new ArrayList<>();
    private TextView timeTextView;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String FRAGMENT_NAME = "ComicFragment";
    
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public TextView getTimeTextView() {
        return timeTextView;
    }
    
    public void setTimeTextView(TextView timeTextView) {
        this.timeTextView = timeTextView;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // 加载保存的时间并显示
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        int totalTime = settings.getInt(FRAGMENT_NAME, 0);
        if (totalTime == 0) {
            timeTextView.setText("今天还没有阅读哦，快开始今天的阅读吧！");
        } else {
            timeTextView.setText(formatTime(totalTime));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (books != null) {    // 解决数据重复的问题
            books.clear();
        }
        
        getBookList();  // 数据库获取书

        GridView gridView = view.findViewById(R.id.home_books);
        gridView.setAdapter(new BookAdapter(books, requireContext()));
        
        timeTextView = view.findViewById(R.id.timeTextView);
        
        gridView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent("com.coop.comics.Activity.ComicActivity");
            intent.putExtra("bookId", books.get(i).getId());  // 传输bookId

            startActivity(intent);  // 启动 ComicActivity
        }); // 点击事件

        return view;
    }
    
    public void getBookList() {
        // 数据库查询所有的书
        books = new BookDao(requireContext()).queryBooks();

    }
    
    public String formatTime(long totalTime) {
        long hours = totalTime / 3600;
        long minutes = (totalTime % 3600) / 60;
        long seconds = totalTime % 60;
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("今日已经阅读了\n");
        
        if (hours > 0) {
            stringBuilder.append(hours).append("小时");
        }
        
        if (minutes > 0) {
            stringBuilder.append(minutes).append("分");
        }
        
        if (seconds > 0 || (hours == 0 && minutes == 0)) {
            stringBuilder.append(seconds).append("秒");
        }
        
        stringBuilder.append("\n每天阅读1小时，你会受益一辈子");
        
        return stringBuilder.toString();
    }
    
}
