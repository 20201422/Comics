package com.coop.comics.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.coop.comics.Adapter.CollectionAdapter;
import com.coop.comics.Dao.PagesDao;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends Fragment {
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ComicData> collections = new ArrayList<>();
    public CollectionFragment() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    public List<ComicData> getCollections() {
        return collections;
    }
    
    public void setCollections(List<ComicData> collections) {
        this.collections = collections;
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
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        if (collections != null) {    // 解决数据重复的问题
            collections.clear();
        }
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView listView = view.findViewById(R.id.collections_list);
        getCollection();
        if (collections.size() != 0) {    // 如果有书签
            listView.setBackgroundResource(R.drawable.border_radius);   // 添加背景
        }

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent("com.coop.comics.Activity.CollectionActivity");
            intent.putExtra("stopPage", collections.get(position).getPage()); // 传输书签的页数
            intent.putExtra("bookId", collections.get(position).getBookId()); // 传输书的id

            startActivity(intent);  // 启动 ComicActivity
        }); // 收藏

        listView.setAdapter(new CollectionAdapter(collections, getContext()));

        return view;
    }
    public void getCollection() {
        // 数据库读取收藏
        PagesDao pagesDao = new PagesDao(requireContext());
        collections = pagesDao.findAllCollection();
    }

}

