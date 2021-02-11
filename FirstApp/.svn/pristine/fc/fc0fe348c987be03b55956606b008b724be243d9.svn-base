package com.example.zhtest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    private TextView tv_content;
    private String strContent;
    private int bgColor;

    public ContentFragment(String strContent,int bgColor) {
        this.strContent = strContent;
        this.bgColor = bgColor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);
        tv_content = (TextView) view.findViewById(R.id.tv_content);

        if(bgColor > 0){
            view.setBackgroundColor(getResources().getColor(bgColor));
        }

        if(null !=getArguments() && !getArguments().getString("text").isEmpty()){
            String text = getArguments().getString("text");
            tv_content.setText(text);
        }else if(null != strContent){
            tv_content.setText(strContent);
        }
        return view;
    }
}
