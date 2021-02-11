package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhtest.model.Animal;
import com.example.zhtest.model.Data;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    private static final int TYPE_DATA = 0;
    private static final int TYPE_ANIMAL = 1;
    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MutiLayoutAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Main12Activity.EXTRA_MESSAGE);
        Toast.makeText(Main4Activity.this, message,
                Toast.LENGTH_SHORT).show();

        //数据准备：
        mData = new ArrayList<Object>();
        for(int i = 0;i < 20;i++){
            switch ((int)(Math.random() * 2)){
                case TYPE_DATA:
                    mData.add(new Data(R.drawable.icon1,"哈哈哈哈"));
                    break;
                case TYPE_ANIMAL:
                    mData.add(new Animal("小狗", "汪汪汪汪", R.drawable.icon2));
                    break;
            }
        }

        list_content = (ListView) findViewById(R.id.list_content);
        myAdapter = new MutiLayoutAdapter(Main4Activity.this,mData);
        list_content.setAdapter(myAdapter);

    }
}
