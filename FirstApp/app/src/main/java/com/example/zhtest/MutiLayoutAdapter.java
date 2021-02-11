package com.example.zhtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhtest.model.Animal;
import com.example.zhtest.model.Data;

import java.util.ArrayList;

public class MutiLayoutAdapter extends BaseAdapter {

    //定义两个类别标志
    private static final int TYPE_DATA = 0;
    private static final int TYPE_ANIMAL = 1;
    private Context mContext;
    private ArrayList<Object> mData = null;


    public MutiLayoutAdapter(Context mContext,ArrayList<Object> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //多布局的核心，通过这个判断类别
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof Data) {
            return TYPE_DATA;
        } else if (mData.get(position) instanceof Animal) {
            return TYPE_ANIMAL;
        } else {
            return super.getItemViewType(position);
        }
    }

    //类别数目
    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        if(convertView == null){
            switch (type){
                case TYPE_DATA:
                    holder1 = new ViewHolder1();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_data, parent, false);
                    holder1.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
                    holder1.txt_aname = (TextView) convertView.findViewById(R.id.txt_content);
                    convertView.setTag(R.id.Tag_Data,holder1);
                    break;
                case TYPE_ANIMAL:
                    holder2 = new ViewHolder2();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_animal, parent, false);
                    holder2.img_bicon = (ImageView) convertView.findViewById(R.id.imgtou);
                    holder2.txt_bname = (TextView) convertView.findViewById(R.id.name);
                    holder2.txt_bsays = (TextView) convertView.findViewById(R.id.says);
                    convertView.setTag(R.id.Tag_Animal,holder2);
                    break;
            }
        }else{
            switch (type){
                case TYPE_DATA:
                    holder1 = (ViewHolder1) convertView.getTag(R.id.Tag_Data);
                    break;
                case TYPE_ANIMAL:
                    holder2 = (ViewHolder2) convertView.getTag(R.id.Tag_Animal);
                    break;
            }
        }

        Object obj = mData.get(position);
        //设置下控件的值
        switch (type){
            case TYPE_DATA:
                Data data = (Data) obj;
                if(data != null){
                    holder1.img_icon.setImageResource(data.getImgId());
                    holder1.txt_aname.setText(data.getContent());
                }
                break;
            case TYPE_ANIMAL:
                Animal animal = (Animal) obj;
                if(animal != null){
                    holder2.img_bicon.setImageResource(animal.getaIcon());
                    holder2.txt_bname.setText(animal.getaName());
                    holder2.txt_bsays.setText(animal.getaSpeak());
                }
                break;
        }
        return convertView;
    }


    //两个不同的ViewHolder
    private static class ViewHolder1{
        ImageView img_icon;
        TextView txt_aname;
    }

    private static class ViewHolder2{
        ImageView img_bicon;
        TextView txt_bname;
        TextView txt_bsays;
    }
}
