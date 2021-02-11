package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Main7Activity extends AppCompatActivity {

    private Context mContext;
    private ViewFlipper vflp_test;
    private int[] resId = {R.mipmap.flower1,R.mipmap.flower2,R.mipmap.flower3,R.mipmap.flower4};

    private final static int MIN_MOVE = 200;   //最小距离
    private MyGestureListener mgListener;
    private GestureDetector mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        mContext = Main7Activity.this;

        initVflp();

    }

    public void initVflp() {

        //实例化SimpleOnGestureListener与GestureDetector对象
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
        //实例化SimpleOnGestureListener与GestureDetector对象
        vflp_test = (ViewFlipper) findViewById(R.id.vflp_test);
        //动态导入添加子View
        for(int i = 0;i < resId.length;i++){
            vflp_test.addView(getImageView(resId[i]));
        }

    }

    //重写onTouchEvent触发MyGestureListener里的方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }


    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if(e1.getX() - e2.getX() > MIN_MOVE){
                vflp_test.setInAnimation(mContext,R.anim.right_in);
                vflp_test.setOutAnimation(mContext, R.anim.right_out);
                vflp_test.showNext();
            }else if(e2.getX() - e1.getX() > MIN_MOVE){
                vflp_test.setInAnimation(mContext,R.anim.left_in);
                vflp_test.setOutAnimation(mContext, R.anim.left_out);
                vflp_test.showPrevious();
            }
            return true;
        }
    }

    private ImageView getImageView(int resId){
        ImageView img = new ImageView(this);
        img.setBackgroundResource(resId);
        return img;
    }
}
