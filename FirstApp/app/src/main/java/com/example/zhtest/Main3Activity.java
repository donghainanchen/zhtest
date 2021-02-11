package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhtest.model.Animal;
import com.example.zhtest.model.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener  {

    private Context mContext;
    private ListView list_data;
    private ListView list_animal;

    private MyAdapter<Data> myAdapterData = null;
    private MyAdapter<Animal> myAdapterAnimal = null;
    private List<Data> mDataData = null;
    private List<Animal> mDataAnimal = null;

    private Button btn_add;
    private Button btn_addx;
    private Button btn_deletex;
    private Button btn_deleteall;
    private int flag = 0;
    private int flag2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_addx = (Button) findViewById(R.id.btn_addx);
        btn_addx.setOnClickListener(this);
        btn_deletex = (Button) findViewById(R.id.btn_deletex);
        btn_deletex.setOnClickListener(this);
        btn_deleteall = (Button) findViewById(R.id.btn_deleteall);
        btn_deleteall.setOnClickListener(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Main12Activity.EXTRA_MESSAGE);
        TextView txt_empty = findViewById(R.id.sendmessage3);
        txt_empty.setText(message);

        mContext = Main3Activity.this;
        init();
        list_data.setEmptyView(txt_empty);
    }

    private void init() {

        list_data = (ListView) findViewById(R.id.list_data);
        list_animal = (ListView) findViewById(R.id.list_animal);

        //数据初始化
        mDataData = new ArrayList<Data>();
        mDataData.add(new Data(R.drawable.icon4,"tu4"));
        mDataData.add(new Data(R.drawable.icon5,"tu5"));
        mDataData.add(new Data(R.drawable.icon1,"tu1"));

        mDataAnimal = new ArrayList<Animal>();
        mDataAnimal.add(new Animal("小狗", "汪汪汪汪", R.drawable.icon1));
        mDataAnimal.add(new Animal("小猫", "喵喵喵喵", R.drawable.icon2));
        mDataAnimal.add(new Animal("小鸡", "叽叽叽叽", R.drawable.icon3));
        //Adapter初始化
        myAdapterData = new MyAdapter<Data>((ArrayList)mDataData,R.layout.list_data) {
            @Override
            public void bindView(ViewHolder holder, Data obj) {
                holder.setImageResource(R.id.img_icon,obj.getImgId());
                holder.setText(R.id.txt_content,obj.getContent());
            }
        };
        myAdapterAnimal = new MyAdapter<Animal>((ArrayList)mDataAnimal,R.layout.list_animal) {
            @Override
            public void bindView(ViewHolder holder, Animal obj) {
                holder.setImageResource(R.id.imgtou,obj.getaIcon());
                holder.setText(R.id.name,obj.getaName());
                holder.setText(R.id.says,obj.getaSpeak());
            }
        };

        //ListView设置下Adapter：
        list_animal.setAdapter(myAdapterAnimal);
        list_data.setAdapter(myAdapterData);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_add:
                myAdapterData.add(flag,new Data(R.drawable.icon1,"嘿嘿加了一个~~~ x " + flag));
                myAdapterAnimal.add(flag2,new Animal("小动物" + flag2, "叽叽叽叽", R.drawable.icon4));
                flag++;
                flag2++;
                break;
            case R.id.btn_addx:

                EditText addText = (EditText) findViewById(R.id.view_number);
                if(null != addText){
                    int addNumber = Integer.parseInt(addText.getText().toString());
                    Toast.makeText(Main3Activity.this, "addNumber=="+addNumber,
                            Toast.LENGTH_SHORT).show();
                    if(addNumber >=0 && flag >= addNumber){
                        myAdapterData.add(addNumber,new Data(R.drawable.icon2,"嘿嘿加了一个~~~ x " + flag));
                        flag++;
                    }else{
                        Toast.makeText(Main3Activity.this, "输入错误",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Main3Activity.this, "输入错误",
                            Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_deletex:
                EditText deleteText = (EditText) findViewById(R.id.view_number);
                if(null != deleteText){
                    int deleteNumber = Integer.parseInt(deleteText.getText().toString());
                    Toast.makeText(Main3Activity.this, "deleteNumber=="+deleteNumber,
                            Toast.LENGTH_SHORT).show();
                    if(deleteNumber >=0 && flag >= deleteNumber){
                        myAdapterData.remove(deleteNumber);
                        flag--;
                    }else{
                        Toast.makeText(Main3Activity.this, "输入错误",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Main3Activity.this, "输入错误",
                            Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_deleteall:
                myAdapterData.clear();
                flag = 0;
                break;
        }
    }
}
