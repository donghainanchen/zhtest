package com.example.zhtest.utils;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {
    //Volley的全局请求队列
    public static RequestQueue sRequestQueue;

    private static MyApplication mInstance;

    /**
     * 获取context
     * @return
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    /**
     * @return Volley全局请求队列
     */
    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //实例化Volley全局请求队列
        sRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }
}
