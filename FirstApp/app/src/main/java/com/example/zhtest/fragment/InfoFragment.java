package com.example.zhtest.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.zhtest.AnimalAdapter;
import com.example.zhtest.Main2Activity;
import com.example.zhtest.R;
import com.example.zhtest.model.Animal;
import com.example.zhtest.utils.BaseFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InfoFragment extends BaseFragment implements View.OnClickListener {
    private Button btn_down;
    private Button btn_up;
    private ScrollView scrollView;
    private TextView txt_show;
    private View view = null;

    ListView contactsview;
    //存放数据
    List<String> contactsList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_main2, container, false);
        String message = "yyy";

        // Capture the layout's TextView and set the string as its text
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(message);

        TextView t1 = (TextView) view.findViewById(R.id.textone);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("好友" + i + "，");
        }

        String likeUsers = sb.substring(0, sb.lastIndexOf("，")).toString();
        t1.setMovementMethod(LinkMovementMethod.getInstance());
        t1.setText(addClickPart(likeUsers), TextView.BufferType.SPANNABLE);

        //跑马灯，前台设置无效
        TextView textView3 =(TextView) view.findViewById(R.id.textView3);
        textView3.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView3.setSingleLine(true);
        textView3.setSelected(true);
        textView3.setFocusable(true);
        textView3.setFocusableInTouchMode(true);

        //ListView
        //创建一个AnimalAdapter
        Context mContext = getContext();
        ListView list_animal = (ListView) view.findViewById(R.id.list_test);
        //动态加载顶部View和底部View
        final LayoutInflater inflater2 = LayoutInflater.from(getContext());
        View headView = inflater2.inflate(R.layout.view_header, null, false);
        View footView = inflater2.inflate(R.layout.view_footer, null, false);

        List<Animal> mData = new LinkedList<Animal>();
        mData.add(new Animal("小狗", "汪汪汪汪", R.drawable.icon1));
        AnimalAdapter mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);

        //添加表头和表尾需要写在setAdapter方法调用之前！！！
        list_animal.addHeaderView(headView);
        list_animal.addFooterView(footView);

        list_animal.setAdapter(mAdapter);

        ListView list_test2 = (ListView) view.findViewById(R.id.list_test2);
        //读取联系人 初始化view
        initview();

        bindViews();
        return view;
    }


    /**
     * 提供Fragment实例
     *
     * @return
     */
    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //定义一个点击每个部分文字的处理方法
    private SpannableStringBuilder addClickPart(String str) {

        ImageSpan imgspan = new ImageSpan(getContext(), R.drawable.icon1);
        SpannableString spanStr = new SpannableString("p.");
        spanStr.setSpan(imgspan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        //创建一个SpannableStringBuilder对象，连接多个字符串
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(str);
        String[] likeUsers = str.split("，");
        if (likeUsers.length > 0) {
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                final int start = str.indexOf(name) + spanStr.length();
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(getContext(), name,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        //删除下划线，设置字体颜色为蓝色
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                },start,start + name.length(),0);
            }
        }
        return ssb.append("等" + likeUsers.length + "个人觉得很赞");
    }

    //展示联系人列表
    private void initview() {
        //获取listview
        contactsview = view.findViewById(R.id.list_test2);
        //适配器是为了将构造函数中把要适配的数据传入 当前提供的数据是字符串 所以泛型为String 第二个参数是子项的布局
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, contactsList);
        contactsview.setAdapter(adapter);
        //判断用户是否已经授权给我们了 如果没有，调用下面方法向用户申请授权，之后系统就会弹出一个权限申请的对话框
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),new String[]{Manifest.permission.READ_CONTACTS},1);
        } else {
            readContacts();
        }
    }

    //调用并获取联系人信息
    private void readContacts() {
        Cursor cursor = null;
        try {

            cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName + "\n" + number);
                }
                //刷新
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //回调方法，无论哪种结果，最终都会回调该方法，之后在判断用户是否授权，
    // 用户同意则调用readContacts（）方法，失败则会弹窗提示失败
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                } else {
                    Toast.makeText(getContext(), "获取联系人权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void bindViews() {
        btn_down = (Button) view.findViewById(R.id.btn_down);
        btn_up = (Button) view.findViewById(R.id.btn_up);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView2);

        txt_show = (TextView) view.findViewById(R.id.txt22);
        btn_down.setOnClickListener(this);
        btn_up.setOnClickListener(this);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            sb.append("呵呵 * " + i + "\n");
        }
        txt_show.setText(sb.toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_down:
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.btn_up:
                scrollView.fullScroll(ScrollView.FOCUS_UP);
                break;
        }
    }
}