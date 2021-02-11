package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhtest.model.Data;
import com.example.zhtest.model.Icon;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter gridAdapter = null;
    private ArrayList<Icon> mIcon = null;

    private Spinner spin_one;
    private Spinner spin_two;
    //判断是否为刚进去时触发onItemSelected的标志
    private boolean one_selected = false;
    private boolean two_selected = false;
    private ArrayList<Data> mData = null;
    private BaseAdapter dataAdadpter = null;

    private AutoCompleteTextView atv_content;
    private MultiAutoCompleteTextView matv_content;

    private static final String[] data = new String[]{
            "小猪猪", "小狗狗", "小鸡鸡", "小猫猫", "小咪咪"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mContext = Main5Activity.this;
        mData = new ArrayList<Data>();

        initGridIcon();
        bindSpin();
        initAutoCompleteTextView();

    }

    //初始化网格图片
    private void initGridIcon() {
        grid_photo = (GridView) findViewById(R.id.grid_photo);

        mIcon = new ArrayList<Icon>();
        mIcon.add(new Icon(R.drawable.icon1, "图标1"));
        mIcon.add(new Icon(R.drawable.icon2, "图标2"));
        mIcon.add(new Icon(R.drawable.icon3, "图标3"));
        mIcon.add(new Icon(R.drawable.icon4, "图标4"));
        mIcon.add(new Icon(R.drawable.icon5, "图标5"));
        mIcon.add(new Icon(R.drawable.icon6, "图标6"));

        gridAdapter = new MyAdapter<Icon>(mIcon, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };

        grid_photo.setAdapter(gridAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化列表选项框)
    private void bindSpin() {
        spin_one = (Spinner) findViewById(R.id.spin_one);
        spin_two = (Spinner) findViewById(R.id.spin_two);


        mData.add(new Data(R.drawable.icon1,"图1"));
        mData.add(new Data(R.drawable.icon2,"图2"));
        mData.add(new Data(R.drawable.icon3,"图3"));
        mData.add(new Data(R.drawable.icon4,"图4"));
        mData.add(new Data(R.drawable.icon5,"图5"));
        mData.add(new Data(R.drawable.icon6,"图6"));
        mData.add(new Data(R.drawable.icon7,"图7"));

        dataAdadpter = new MyAdapter<Data>(mData,R.layout.list_data) {
            @Override
            public void bindView(ViewHolder holder, Data obj) {
                holder.setImageResource(R.id.img_icon,obj.getImgId());
                holder.setText(R.id.txt_content, obj.getContent());
            }
        };
        spin_two.setAdapter(dataAdadpter);
        spin_one.setOnItemSelectedListener(this);
        spin_two.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spin_one:
                if(one_selected){
                    Toast.makeText(mContext,"您的科目是~：" + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
                }else one_selected = true;
                break;
            case R.id.spin_two:
                if(two_selected){
                    TextView txt_name = (TextView) view.findViewById(R.id.txt_content);
                    Toast.makeText(mContext,"您选择的图标是~：" + txt_name.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                }else two_selected = true;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //初始化自动完成文本框
    private void initAutoCompleteTextView() {
        atv_content = (AutoCompleteTextView) findViewById(R.id.atv_content);
        matv_content = (MultiAutoCompleteTextView) findViewById(R.id.matv_content);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main5Activity.
                this, android.R.layout.simple_dropdown_item_1line, data);
        atv_content.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, data);
        matv_content.setAdapter(adapter);
        matv_content.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
