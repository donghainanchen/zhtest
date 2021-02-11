package com.example.zhtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.zhtest.model.Animal;
import com.example.zhtest.model.Data;
import com.example.zhtest.model.SendButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main12Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private Context mContext;
    private ListView list_sendbutton;

    private EditTextAdapter editTextAdapter = null;
    private ArrayList<SendButton> mDataSendButton = null;

    private NotificationManager mNManager;
    private Notification notification;
    Bitmap LargeBitmap = null;
    private static final int NOTIFYID_1 = 1;

    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    private View view_custom;

    private ProgressDialog pd1 = null;
    private ProgressDialog pd2 = null;
    private final static int MAXVALUE = 100;
    private int progressStart = 0;
    private int addcount = 0;

    private String timeResult = "";

    private DrawerLayout drawer_layout;
    private ListView list_left_drawer;
    private ArrayList<Data> menuLists;
    private MyAdapter<Data> myAdapter = null;

    //定义一个用于更新进度的Handler,因为只能由主线程更新界面,所以要用Handler传递信息
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            //这里的话如果接受到信息码是123
            if(msg.what == 123)
            {
                //设置进度条的当前值
                pd2.setProgress(progressStart);
            }
            //如果当前大于或等于进度条的最大值,调用dismiss()方法关闭对话框
            if(progressStart >= MAXVALUE)
            {
                pd2.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = Main12Activity.this;

        initList();
        initDrawerLayout();
    }

    private void initList() {

        list_sendbutton = (ListView) findViewById(R.id.list_sendbutton);
        list_sendbutton.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("mm", " onItemClick2 " + position);
                Toast.makeText(Main12Activity.this, "toast: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        mDataSendButton = new ArrayList<SendButton>();
        for (int i = 0; i < 26; i++) {
            SendButton itemTest = new SendButton();
            itemTest.setlistedit("message" + i);
            itemTest.setlistbutton("send" + i);
            itemTest.setlistonClick("sendMessage");
            mDataSendButton.add(itemTest);
        }
        editTextAdapter = new EditTextAdapter( mDataSendButton,this);
        list_sendbutton.setAdapter(editTextAdapter);


    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {

        Button button = (Button) view;
        int number = (int) button.getTag();
        Log.i("sendMessage", "number== " + number);

        String sendMessage = mDataSendButton.get(number).getlistedit().toString();

        Intent intent =null;
        switch (number) {
            case 0:
                intent = new Intent(this, Main2Activity.class);
                break;
            case 1:
                intent = new Intent(this, Main3Activity.class);
                break;
            case 2:
                intent = new Intent(this, Main4Activity.class);
                break;
            case 3:
                intent = new Intent(this, Main5Activity.class);
                break;
            case 4:
                intent = new Intent(this, Main6Activity.class);
                break;
            case 5:
                intent = new Intent(this, Main7Activity.class);
                break;
            case 6:
                Log.i("Notification", "Build.VERSION.SDK_IN== " + Build.VERSION.SDK_INT+"==Build.VERSION_CODES.O== " + Build.VERSION_CODES.O);
                String channelId ="my channel"; //通知id
                String channelName="渠道名字";
                //创建大图标的Bitmap
                LargeBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bird1);
                //定义一个PendingIntent点击Notification后启动一个Activity
                Intent it = new Intent(mContext, Main7Activity.class);
                PendingIntent pit = PendingIntent.getActivity(mContext, 0, it, 0);
                mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    NotificationChannel mChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);

                    mNManager.createNotificationChannel(mChannel);
                    //设置图片,通知标题,发送时间,提示方式等属性
                    notification = new NotificationCompat.Builder(this,channelId)
                            .setContentTitle("叶良辰")                        //标题
                            .setContentText("我有一百种方法让你呆不下去~")      //内容
                            .setSubText("——记住我叫叶良辰")                    //内容下面的一小段文字
                            .setTicker("收到叶良辰发送过来的信息~")             //收到信息后状态栏显示的文字信息
                            .setWhen(System.currentTimeMillis())           //设置通知时间
                            .setSmallIcon(R.drawable.icon1)            //设置小图标
                            .setLargeIcon(LargeBitmap)                     //设置大图标
                            .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
                            .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao))  //设置自定义的提示音
                            .setAutoCancel(true)                           //设置点击后取消Notification
                            .setContentIntent(pit)//添加点击跳转通知跳转
                            .build();                        //设置PendingIntent

                }else{
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"default")
                            .setContentTitle("5 new messages")
                            .setContentText("hahaha")
                            .setSmallIcon(R.mipmap.acorn1)
                            .setOngoing(true)
                            .setChannelId(channelId);//无效
                    notification = notificationBuilder.build();
                }

                mNManager.notify(NOTIFYID_1,notification);

                break;
            case 7:
                //除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
                if(null != notification){
                    mNManager.cancel(NOTIFYID_1);                          //取消Notification
                }
                break;
            //普通对话框
            case 8:
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.mipmap.bird2)
                        .setTitle("系统提示：")
                        .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext, "你点击了中立按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).create();             //创建AlertDialog对象
                alert.show();                    //显示对话框
                break;
            //普通列表对话框
            case 9:
                final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.mipmap.flower1)
                        .setTitle("选择你喜欢的课程")
                        .setItems(lesson, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            //单选列表对话框
            case 10:
                final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.mipmap.flower2)
                        .setTitle("选择你喜欢的水果，只能选一个哦~")
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + fruits[which], Toast.LENGTH_SHORT).show();
                                alert.hide();
                            }
                        }).create();
                alert.show();
                break;
            //多选列表对话框
            case 11:
                final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
                //定义一个用来记录个列表项状态的boolean数组
                checkItems = new boolean[]{false, false, false, false};
                alert = null;
                builder = new AlertDialog.Builder(mContext);
                alert = builder.setIcon(R.mipmap.flower3)
                        .setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = "";
                                for (int i = 0; i < checkItems.length; i++) {
                                    if (checkItems[i])
                                        result += menu[i] + " ";
                                }
                                Toast.makeText(getApplicationContext(), "客官你点了:" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alert.show();
                break;
            case 12:
                //初始化Builder
                builder = new AlertDialog.Builder(mContext);

                //加载自定义的那个View,同时设置下
                final LayoutInflater inflater = Main12Activity.this.getLayoutInflater();
                view_custom = inflater.inflate(R.layout.view_dialog_custom, null,false);
                builder.setView(view_custom);
                builder.setCancelable(false);
                alert = builder.create();

                view_custom.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });

                view_custom.findViewById(R.id.btn_blog).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "访问博客", Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse("http://blog.csdn.net/coder_pig");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        alert.dismiss();
                    }
                });

                view_custom.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "对话框已关闭~", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });

                alert.show();
                break;

            case 13:
                //这里的话参数依次为,上下文,标题,内容,是否显示进度,是否可以用取消按钮关闭
                ProgressDialog.show(Main12Activity.this, "资源加载中", "资源加载中,请稍后...",false,true);
                break;

            case 14:
                pd1 = new ProgressDialog(mContext);
                //依次设置标题,内容,是否用取消按钮关闭,是否显示进度
                pd1.setTitle("软件更新中");
                pd1.setMessage("软件正在更新中,请稍后...");
                pd1.setCancelable(true);
                //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
                pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd1.setIndeterminate(true);
                //调用show()方法将ProgressDialog显示出来
                pd1.show();
                break;
            case 15:
                //初始化属性
                progressStart = 0;
                addcount = 0;
                //依次设置一些属性
                pd2 = new ProgressDialog(Main12Activity.this);
                pd2.setMax(MAXVALUE);
                pd2.setTitle("文件读取中");
                pd2.setMessage("文件加载中,请稍后...");
                //这里设置为不可以通过按取消按钮关闭进度条
                pd2.setCancelable(false);
                pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //这里设置的是是否显示进度,设为false才是显示的哦！
                pd2.setIndeterminate(false);
                pd2.show();
                //这里的话新建一个线程,重写run()方法,
                new Thread()
                {
                    public void run()
                    {
                        while(progressStart < MAXVALUE)
                        {
                            //这里的算法是决定进度条变化的,可以按需要写
                            progressStart = 2 * usetime() ;
                            //把信息码发送给handle让更新界面
                            hand.sendEmptyMessage(123);
                        }
                    }
                }.start();
                break;

            case 16:
                Calendar cale1 = Calendar.getInstance();
                new DatePickerDialog(Main12Activity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        //这里获取到的月份需要加上1哦~
                        timeResult += "你选择的是"+year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日";
                        Toast.makeText(getApplicationContext(), timeResult, Toast.LENGTH_SHORT).show();
                    }
                }
                        ,cale1.get(Calendar.YEAR)
                        ,cale1.get(Calendar.MONTH)
                        ,cale1.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case 17:
                Calendar cale2 = Calendar.getInstance();
                new TimePickerDialog(Main12Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeResult = "";
                        timeResult += "您选择的时间是:"+hourOfDay+"时"+minute+"分";
                        Toast.makeText(getApplicationContext(), timeResult, Toast.LENGTH_SHORT).show();
                    }
                }, cale2.get(Calendar.HOUR_OF_DAY), cale2.get(Calendar.MINUTE), true).show();
                break;
            case 18:
                initPopWindow(view);
                break;
            case 19:
                intent = new Intent(this, Main8Activity.class);
                break;
            case 20:
                intent = new Intent(this, Main9Activity.class);
                break;
            case 21:
                intent = new Intent(this, Main10Activity.class);
                break;
            case 22:
                intent = new Intent(this, Main11Activity.class);
                break;
            case 23:
                intent = new Intent(this, Main12Activity.class);
                break;
            default :
                midToast("点击！", Toast.LENGTH_SHORT);
        }
        if(null != intent){
            intent.putExtra(EXTRA_MESSAGE, sendMessage);
            startActivity(intent);
        }

    }

    private void midToast(String str, int showTime) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.view_toast_custom,
                (ViewGroup) findViewById(R.id.lly_toast));
        ImageView img_logo = (ImageView) view.findViewById(R.id.tv_img_logo);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        tv_msg.setText(str);
        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(showTime);
        toast.setView(view);
        toast.show();
    }

    //这里设置一个耗时的方法:
    private int usetime() {
        addcount++;
        try{
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addcount;
    }

    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        Button btn_xixi = (Button) view.findViewById(R.id.btn_xixi);
        Button btn_hehe = (Button) view.findViewById(R.id.btn_hehe);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 50, 0);

        //设置popupWindow里的按钮的事件
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main12Activity.this, "你点击了嘻嘻~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main12Activity.this, "你点击了呵呵~", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }

    private void initDrawerLayout(){
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout0);
        list_left_drawer = (ListView) findViewById(R.id.list_left_drawer);

        menuLists = new ArrayList<Data>();
        menuLists.add(new Data(R.mipmap.nature1,"实时信息"));
        menuLists.add(new Data(R.mipmap.nature2,"提醒通知"));
        menuLists.add(new Data(R.mipmap.nature3,"活动路线"));
        menuLists.add(new Data(R.mipmap.nature4,"相关设置"));
        myAdapter = new MyAdapter<Data>(menuLists,R.layout.list_data) {
            @Override
            public void bindView(ViewHolder holder, Data obj) {
                holder.setImageResource(R.id.img_icon,obj.getImgId());
                holder.setText(R.id.txt_content, obj.getContent());
            }
        };
        list_left_drawer.setAdapter(myAdapter);
        list_left_drawer.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle args = new Bundle();
        args.putString("text", menuLists.get(position).getContent());
        ContentFragment contentFragment = new ContentFragment("",0);
        contentFragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.ly12_content,contentFragment).commit();

        drawer_layout.closeDrawer(list_left_drawer);
    }
}
