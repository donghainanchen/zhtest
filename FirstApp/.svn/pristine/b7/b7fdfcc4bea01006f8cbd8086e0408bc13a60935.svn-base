package com.example.zhtest;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhtest.model.MyTextWatcher;
import com.example.zhtest.model.SendButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EditTextAdapter extends BaseAdapter implements View.OnClickListener, View.OnTouchListener, View.OnFocusChangeListener, View.OnLongClickListener {
    private int selectedEditTextPosition = -1;
    private int selectedEditButtonPosition = -1;
    private ArrayList<SendButton> mList;
    private Context mContext;

    public EditTextAdapter(ArrayList<SendButton> mList, Context mContext) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_sendbutton, null);
            vh = new ViewHolder();
            vh.editText = (EditText) convertView.findViewById(R.id.listedit);
            vh.sendButton = (Button) convertView.findViewById(R.id.listbutton);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.editText.setOnTouchListener(this); // 正确写法
        vh.editText.setOnFocusChangeListener(this);
        vh.editText.setTag(position);

        vh.sendButton.setTag(position);

        if (selectedEditTextPosition != -1 && position == selectedEditTextPosition) { // 保证每个时刻只有一个EditText能获取到焦点
            vh.editText.requestFocus();
        } else {
            vh.editText.clearFocus();
        }

        String text = mList.get(position).getlistedit();
        vh.editText.setText(text);
        vh.editText.setSelection(vh.editText.length());

        vh.sendButton.setText(mList.get(position).getlistbutton());

        convertView.setTag(R.id.list_sendbutton, position); // 应该在这里让convertView绑定position
        convertView.setOnClickListener(this);
        convertView.setOnLongClickListener(this);
        return convertView;
    }

    private TextWatcher mTextWatcher = new MyTextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (selectedEditTextPosition != -1) {
                SendButton itemTest = (SendButton) getItem(selectedEditTextPosition);
                itemTest.setlistedit(s.toString());
            }
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            EditText editText = (EditText) v;
            selectedEditTextPosition = (int) editText.getTag();
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText editText = (EditText) v;
        if (hasFocus) {
            editText.addTextChangedListener(mTextWatcher);
        } else {
            editText.removeTextChangedListener(mTextWatcher);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.list_sendbutton) {
            int position = (int) view.getTag(R.id.list_sendbutton);
            Toast.makeText(mContext, "点击第 " + position + " 个item", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.list_sendbutton) {
            int position = (int) view.getTag(R.id.list_sendbutton);
            Toast.makeText(mContext, "长按第 " + position + " 个item", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    static class ViewHolder {
        EditText editText;
        Button sendButton;
    }
}
