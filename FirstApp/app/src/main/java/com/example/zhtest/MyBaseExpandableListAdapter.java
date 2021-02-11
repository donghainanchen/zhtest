package com.example.zhtest;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhtest.model.Animal;
import com.example.zhtest.model.Data;

import java.security.acl.Group;
import java.util.ArrayList;

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private ArrayList<Data> gData;
    private ArrayList<ArrayList<Animal>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<Data> gData, ArrayList<ArrayList<Animal>> iData, Context mContext) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Data getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Animal getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_data, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.group_img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.group_img_icon.setImageResource(gData.get(groupPosition).getImgId());
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getContent());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_animal, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = (ImageView) convertView.findViewById(R.id.imgtou);
            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.name);
            itemHolder.tv_says = (TextView) convertView.findViewById(R.id.says);
            convertView.setTag(itemHolder);
        }else{
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getaIcon());
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getaName());
        itemHolder.tv_says.setText(iData.get(groupPosition).get(childPosition).getaSpeak());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup{
        private ImageView group_img_icon;
        private TextView tv_group_name;
    }

    private static class ViewHolderItem{
        private ImageView img_icon;
        private TextView tv_name;
        private TextView tv_says;
    }

}
