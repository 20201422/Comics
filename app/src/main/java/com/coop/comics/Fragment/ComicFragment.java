/**
 * @ClassName ComicFragment
 * @Author 24
 * @Date 2023/5/10 14:36
 * @Version 1.0.0
 * freedom is the oxygen of the soul.
 **/

package com.coop.comics.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.coop.comics.R;

public class ComicFragment extends Fragment {
    
    private int imageResId;
    private String title;
    private String summary;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView titleView = view.findViewById(R.id.title_view);
        TextView summaryView = view.findViewById(R.id.summary_view);
        
        Bundle args = getArguments();
        if (args != null) {
            imageResId = args.getInt("imageResId");
            title = args.getString("title");
            summary = args.getString("summary");
        }
        
        imageView.setImageResource(imageResId);
        titleView.setText(title);
        summaryView.setText(summary);
        
        return view;
    }

}

//    may the force be with you.
//    @ClassName   ComicFragment
//    Created by 24 on 2023/5/10.
