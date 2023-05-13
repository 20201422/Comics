package com.coop.comics.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.coop.comics.Adapter.BookmarkAdapter;
import com.coop.comics.Model.Bookmark;
import com.coop.comics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookmarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookmarkFragment extends Fragment {
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    private List<Bookmark> bookmarks = new ArrayList<>();
    
    public BookmarkFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookmarkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookmarkFragment newInstance(String param1, String param2) {
        BookmarkFragment fragment = new BookmarkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }
    
    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        
        if (bookmarks != null) {    // 解决数据重复的问题
            bookmarks.clear();
        }
        
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView listView = view.findViewById(R.id.bookmark_list);
        
        getBookmark(); // 数据库读取书签
        
        listView.setAdapter(new BookmarkAdapter(bookmarks, getContext()));
        
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent("com.coop.comics.Activity.ComicActivity");
            intent.putExtra("stopPage", bookmarks.get(position).getPage()); // 传输书签的页数
            
            startActivity(intent);  // 启动 ComicActivity
        }); // 点击书签
        
        return view;
    }
    
    public void getBookmark() {
        // 数据库读取书签
        bookmarks.add(new Bookmark(1,"三体",2));
        bookmarks.add(new Bookmark(2, "三体", 3));
    }
}
