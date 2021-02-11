package com.example.zhtest.utils;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class PostUtils {
    private static String TAG = "提示";
    private static String msg = "";
    private static final String LOGIN_URL = "http://192.168.31.59:8080/suiyong/html/zz/login";
    private static final String TEST_URL = "http://192.168.31.59:8080/suiyong/html/zz/login";

    public static String LoginByPost(RequestQueue queue, String number, String passwd) throws JSONException {


        Log.i("====LoginByPost====","====0====");
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("gszt",1572921344000000000l);
        jsonObject.put("mc","副总经理");
        jsonObject.put("msgCode","1");
        jsonObject.put("mm", MyUtil.md5("123456").toUpperCase());
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                        msg =response.toString();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {
            //注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
            //必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
            //所以这个方法在此处是不需要的
//    @Override
//    protected Map<String, String> getParams() {
//          Map<String, String> map = new HashMap<String, String>();
//            map.put("name1", "value1");
//            map.put("name2", "value2");

//        return params;
//    }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
        };
        queue.add(jsonRequest);
        return msg;
//        String paramdata ="";
//        Log.i("====LoginByPost====","====0====");
//        JSONObject object =new JSONObject();
//        object.put("gszt",1572921344000000000l);
//        object.put("mc","副总经理");
//        object.put("msgCode","1");
//        object.put("mm", MyUtil.md5("123456").toUpperCase());
//
//        try{
//            HttpURLConnection conn = (HttpURLConnection) new URL(LOGIN_URL).openConnection();
//            //设置请求方式,请求超时信息
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Connection", "Keep-Alive");
//            conn.setReadTimeout(5000);
//            conn.setConnectTimeout(5000);
//            //设置运行输入,输出:
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 设置contentType
//            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
//            //Post方式不能缓存,需手动设置为false
//            conn.setUseCaches(false);
//            //我们请求的数据:
//
//
//            //这里可以写一些请求头的东东...
//            //获取输出流
//            DataOutputStream os = new DataOutputStream( conn.getOutputStream());
////            paramdata = URLEncoder.encode(object.toString(), "UTF-8");
//            paramdata = String.valueOf(object);
//            os.writeBytes(paramdata);
////            os.write(jsonToUrlParams(params).getBytes("UTF-8"));
//            os.flush();
//            os.close();
//            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                // 获取响应的输入流对象
//                InputStream is = conn.getInputStream();
//                // 创建字节输出流对象
//                ByteArrayOutputStream message = new ByteArrayOutputStream();
//                // 定义读取的长度
//                int len = 0;
//                // 定义缓冲区
//                byte buffer[] = new byte[1024];
//                // 按照缓冲区的大小，循环读取
//                while ((len = is.read(buffer)) != -1) {
//                    // 根据读取的长度写入到os对象中
//                    message.write(buffer, 0, len);
//                }
//                // 释放资源
//                is.close();
//                message.close();
//                // 返回字符串
//                msg = new String(message.toByteArray());
//                return msg;
//            }
//        }catch(Exception e){e.printStackTrace();}
//        return msg;
    }
}
