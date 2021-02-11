package com.example.zhtest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private String content;
    public MyFragment(String content) {
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        switch (content) {
            case "1":
                view = inflater.inflate(R.layout.activity_main12,container,false);
                break;
            case "2":
                view = inflater.inflate(R.layout.activity_main3,container,false);
                break;
            case "3":
                view = inflater.inflate(R.layout.activity_main4,container,false);
                break;
            case "4":
                view = inflater.inflate(R.layout.activity_main5,container,false);
                break;
            default:
                view = inflater.inflate(R.layout.activity_main12,container,false);
                break;
        }

        return view;
    }
}
