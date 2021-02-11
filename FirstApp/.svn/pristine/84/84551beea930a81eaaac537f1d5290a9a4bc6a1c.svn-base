package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.zhtest.model.Animal;
import com.example.zhtest.model.Data;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {

    private ArrayList<Data> gData = null;
    private ArrayList<ArrayList<Animal>> iData = null;
    private ArrayList<Animal> lData = null;
    private Context mContext;
    private ExpandableListView exlist_test;
    private MyBaseExpandableListAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mContext = Main6Activity.this;
        exlist_test = (ExpandableListView) findViewById(R.id.exlist_test);

        initExpandableListView();
    }

    //初始化可折叠列表)
    private void initExpandableListView() {
        //数据准备
        gData = new ArrayList<Data>();
        iData = new ArrayList<ArrayList<Animal>>();
        gData.add(new Data(R.drawable.icon1,"动物1"));
        gData.add(new Data(R.drawable.icon2,"动物2"));
        gData.add(new Data(R.drawable.icon3,"动物3"));



        //1组
        lData = new ArrayList<Animal>();
        lData.add(new Animal("小狗1", "汪汪汪汪", R.drawable.icon1));
        lData.add(new Animal("小狗2", "汪汪汪汪", R.drawable.icon2));
        lData.add(new Animal("小狗3", "汪汪汪汪", R.drawable.icon3));
        lData.add(new Animal("小狗4", "汪汪汪汪", R.drawable.icon4));
        iData.add(lData);
        //2组
        lData = new ArrayList<Animal>();
        lData.add(new Animal("小猫1", "喵喵喵喵", R.drawable.icon5));
        lData.add(new Animal("小猫2", "喵喵喵喵", R.drawable.icon6));
        iData.add(lData);
        //3组
        lData = new ArrayList<Animal>();
        lData.add(new Animal("小鸡1", "叽叽叽叽", R.drawable.icon7));
        lData.add(new Animal("小鸡2", "叽叽叽叽", R.drawable.icon8));
        lData.add(new Animal("小鸡3", "叽叽叽叽", R.drawable.icon7));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_test.setAdapter(myAdapter);

        //为列表设置点击事件
        exlist_test.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getaName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
