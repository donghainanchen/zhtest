package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.zhtest.utils.GetData;
import com.example.zhtest.utils.MyUtil;
import com.example.zhtest.utils.PostUtils;
import com.example.zhtest.utils.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;
import java.util.Map;

public class Main11Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText param_name;
    private EditText param_pwd;
    private Button btn_webtest;
    private Button btn_gettest;
    private Button btn_posttest;
    private Button btn_volleytest;
    private Button btn_volleylogouttest;
    private Button btn_volleyquerytest;
    private TextView webtest_result;

    private String name;
    private String pwd;
    private String result;

    private String detail = "";
    private boolean flag = false;
    private final static String GET_URL = "http://192.168.31.59:8080/suiyong/html/zz/getZtqdsdl";
    private final static String POST_URL = "http://192.168.31.59:8080/suiyong/html/zz/login";


    private RequestQueue queue;
    //定义获取信息的SoapAction与命名空间,作为常量
    private static final String AddressnameSpace = "http://service.suiyong.com/";
    //webservice相关参数
    private static final String url = "http://192.168.31.59:8989/webservice?wsdl";
    private static final String funName = "save";
    private static final String soapAction = "http://service.suiyong.com/save";


    //定义一个Handler用来更新页面：
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    webtest_result.setText("结果显示：\n" + result);
                    Toast.makeText(Main11Activity.this, "获取webservice信息成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x002:
                    webtest_result.setText("结果显示：\n" + result);
                    Toast.makeText(Main11Activity.this, "GET获取信息成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    webtest_result.setText("结果显示：\n" + result);
                    Toast.makeText(Main11Activity.this, "POST获取信息成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x004:
                    webtest_result.setText("结果显示：\n" + result);
                    Toast.makeText(Main11Activity.this, "volley获取信息成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x005:
                    webtest_result.setText("结果显示：\n" + result);
                    Toast.makeText(Main11Activity.this, "退出成功", Toast.LENGTH_SHORT).show();
                    break;
                case 0x006:
                    webtest_result.setText("结果显示：\n" + result);
                    Toast.makeText(Main11Activity.this, "查询成功", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        init_config(getApplication());//初始化
        queue = Volley.newRequestQueue(this);
        bindViews();
    }

    private void bindViews() {
        param_name = (EditText) findViewById(R.id.param_name);
        param_pwd = (EditText) findViewById(R.id.param_pwd);
        btn_webtest = (Button) findViewById(R.id.btn_webtest);
        btn_gettest = (Button) findViewById(R.id.btn_gettest);
        btn_posttest = (Button) findViewById(R.id.btn_posttest);
        btn_volleytest = (Button) findViewById(R.id.btn_volleytest);
        btn_volleylogouttest = (Button) findViewById(R.id.btn_volleylogouttest);
        btn_volleyquerytest = (Button) findViewById(R.id.btn_volleyquerytest);
        webtest_result = (TextView) findViewById(R.id.webtest_result);
        btn_webtest.setOnClickListener(this);
        btn_gettest.setOnClickListener(this);
        btn_posttest.setOnClickListener(this);
        btn_volleytest.setOnClickListener(this);
        btn_volleylogouttest.setOnClickListener(this);
        btn_volleyquerytest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_webtest:
                new Thread() {
                    @Override
                    public void run() {
                        getWebservice();
                    }
                }.start();
                break;
            case R.id.btn_gettest:
                new Thread() {
                    public void run() {
                        try {
                            result = GetData.getHtml(GET_URL);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x002);
                    }
                }.start();
                break;
            case R.id.btn_posttest:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            result = PostUtils.LoginByPost(queue, "e", "e");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(0x003);
                    }
                }.start();
                break;
            case R.id.btn_volleytest:

                Map<String, String> map = new HashMap<>();
                map.put("gszt", "1572921344000000000");
                map.put("mc", "副总经理");
                map.put("msgCode", "1");
                map.put("mm", MyUtil.md5("123456").toUpperCase());
                //发送请求
                VolleyUtil.getInstance().simple_post(POST_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("TAG", "onResponse: " + response);
                                result = response;
                                handler.sendEmptyMessage(0x004);
                                Toast.makeText(Main11Activity.this, "请求成功",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError arg0) {
                                Toast.makeText(Main11Activity.this, "网络错误！",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }, map);


                break;
            case R.id.btn_volleylogouttest:

                Map<String, String> map2 = new HashMap<>();
                map2.put("gszt", "1572921344000000000");
                map2.put("mc", "副总经理");
                map2.put("msgCode", "1");
                map2.put("mm", MyUtil.md5("123456").toUpperCase());
                //发送请求
                VolleyUtil.getInstance().simple_post("http://192.168.31.59:8080/suiyong/html/zz/logout",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("TAG", "onResponse: " + response);
                                result = response;
                                handler.sendEmptyMessage(0x005);
                                Toast.makeText(Main11Activity.this, "请求成功",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError arg0) {
                                Toast.makeText(Main11Activity.this, "网络错误！",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }, map2);


                break;
            case R.id.btn_volleyquerytest:

                Map<String, String> map3 = new HashMap<>();
                map3.put("gszt", "1572921344000000000");
                map3.put("mc", "副总经理");
                map3.put("msgCode", "1");
                map3.put("mm", MyUtil.md5("123456").toUpperCase());
                //发送请求
                VolleyUtil.getInstance().simple_post("http://192.168.31.59:8080/suiyong/html/gs/getMenus",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("TAG", "onResponse: " + response);
                                result = response;
                                handler.sendEmptyMessage(0x006);
                                Toast.makeText(Main11Activity.this, "请求成功",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError arg0) {
                                Toast.makeText(Main11Activity.this, "网络错误！",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }, map3);


                break;
            default:
                break;
        }

    }


    //定义一个获取webservice信息的方法：
    public void getWebservice() {
        Log.i("getWebservice", "===0====");
        result = "";
        SoapObject soapObject = new SoapObject(AddressnameSpace, funName);
        soapObject.addProperty("arg0", param_name.getText().toString());
        soapObject.addProperty("arg1", param_pwd.getText().toString());

        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;//由于是发送请求，所以是设置bodyOut
        //envelope.dotNet = true;//由于不是.net开发的webservice，所以这里注释
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        System.out.println("服务设置完毕,准备开启服务");
        try {
            httpTransportSE.call(null, envelope);
            System.out.println("调用WebService服务成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用WebService服务失败");
        }

        //获得服务返回的数据,并且开始解析
        SoapObject object = (SoapObject) envelope.bodyIn;
        System.out.println("获得服务数据");
        result = object.getProperty(0).toString();
        handler.sendEmptyMessage(0x001);
        System.out.println("发送完毕,textview显示save Success");
    }

    /**
     * 初始化设置
     *
     * @param context
     */
    public void init_config(Context context) {
        VolleyUtil.getInstance().init(context);
    }


}
