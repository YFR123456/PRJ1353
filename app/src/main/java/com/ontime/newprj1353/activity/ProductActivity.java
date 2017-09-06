package com.ontime.newprj1353.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ontime.newprj1353.R;
import com.ontime.newprj1353.adapter.MyViewPagerAdapter;
import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductActivity extends AppCompatActivity {

    @InjectView(R.id.viewpager)
    ViewPager viewPager;
    @InjectView(R.id.ll_container)
    LinearLayout llContainer;
    @InjectView(R.id.phone_layout)
    RelativeLayout phoneLayout;
    @InjectView(R.id.tv_count_time)
    TextView tvCountTime;
    //五个页面的列表
    private List<View> list;
    private MyViewPagerAdapter adapter;
    //包含指示小圆点的容器

    private ImageView[] dots;

    private MyCount myCount;

    private ImageView ivIphone;

    private String BluetoothTagId;

    private Boolean flag = false;

    private EditText etBluetoothTagId;

    private Timer timer;

    private String Tag = "ProductActivity";

    private String jsonResultStr;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    flag = (Boolean) msg.obj;
                    if (flag) {
                        tvCountTime.setVisibility(View.VISIBLE);
                    }
                    break;
                case 2:
                    jsonResultStr = (String) msg.obj;
                    Log.d("jsonResultStr", jsonResultStr);
                    Picasso.with(ProductActivity.this).load(ProductListActivity.picOneUrl).into(ivPicOne);
                    Picasso.with(ProductActivity.this).load(ProductListActivity.picTwoUrl).into(ivPicTwo);
                    Picasso.with(ProductActivity.this).load(ProductListActivity.picThreeUrl).into(ivPicThree);
                    Picasso.with(ProductActivity.this).load(ProductListActivity.picFourUrl).into(ivPicFour);
                    Picasso.with(ProductActivity.this).load(ProductListActivity.picFiveUrl).into(ivPicFive);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //cn.manytag.ishw6003.webservice
    private static final String SERVICE_NAMESPACE = "http://webservice.ishw6003.manytag.cn";
    //URL
    private static final String SERVICE_URL = "http://139.224.104.4:8087/iSHW6003/services/IshowService";
    // private   String PROPERTY = "{\"userName\":\"prj1353\",\"passWord\":\"123456\",\"designVersion\":\"desing.000001\",\"softVersion\":\"000008\",\"dataBaseVersion\":\"20170617233733\"}";
    private String PROPERTY = "";
    private static final String METHODNAME = "IshowService1";
    private ImageView ivPicOne;
    private ImageView ivPicTwo;
    private ImageView ivPicThree;
    private ImageView ivPicFour;
    private ImageView ivPicFive;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Tag, "on Create!");
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏状态栏
        setContentView(R.layout.activity_product);
        ButterKnife.inject(this);

        sp = getSharedPreferences("User", MODE_PRIVATE);

        String userName = sp.getString("username", "username");
        String passWord = sp.getString("password", "password");
        Log.d("username", userName);
        Log.d("password", passWord);
        PROPERTY = "{\"userName\":\"" + userName + "\",\"passWord\":\"" + passWord + "\",\"designVersion\":\"desing.000001\",\"softVersion\":\"000008\",\"dataBaseVersion\":\"20170617233733\"}";
        //Log.d("property",property);
        Log.d("PROPERTY", PROPERTY);

        //创建HttpTransportSe对象
        final HttpTransportSE httpTransportSE = new HttpTransportSE(SERVICE_URL);
        httpTransportSE.debug = true;

        //使用soap1.1协议创建Envelop对象
        final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        //实例化SoapObject对象
        SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, METHODNAME);

        //设置参数
        soapObject.addProperty("cmd", "getUpdateScript");
        soapObject.addProperty("jsonPara", PROPERTY);

        //将SoapObject对象设置为SoapSerializationEnvelope对象的传出soap消息
        envelope.bodyOut = soapObject;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpTransportSE.call(null, envelope);
                    Log.d("envelope.getResponse()", envelope.getResponse().toString());
                    if (envelope.getResponse() != null) {
                        SoapObject result = (SoapObject) envelope.bodyIn;
                        Log.d("result---------------", result.toString());
                        Log.d("result", result.getProperty("out").toString());
                        Message message = handler.obtainMessage();
                        message.what = 2;
                        message.obj = result.getProperty("out").toString();
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("ProductActivity", "IOException");
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    Log.d("ProductActivity", "XmlPullParserException");
                }
            }
        }).start();


        sp = getSharedPreferences("BluetoothTagIds", MODE_PRIVATE);
        // BluetoothTagId = sp.getString("BluetoothTagId","03003c4d");

        tvCountTime.setVisibility(View.GONE);
        list = new ArrayList<>();
        View firstView = LayoutInflater.from(this).inflate(R.layout.layout_one, null);
        View secondView = LayoutInflater.from(this).inflate(R.layout.layout_two, null);
        View thirdView = LayoutInflater.from(this).inflate(R.layout.layout_three, null);
        View forthView = LayoutInflater.from(this).inflate(R.layout.layout_four, null);
        View fifthView = LayoutInflater.from(this).inflate(R.layout.layout_five, null);
        ivPicOne = (ImageView) firstView.findViewById(R.id.iv_one);
        ivPicTwo = (ImageView) secondView.findViewById(R.id.iv_two);
        ivPicThree = (ImageView) thirdView.findViewById(R.id.iv_three);
        ivPicFour = (ImageView) forthView.findViewById(R.id.iv_four);
        ivPicFive = (ImageView) fifthView.findViewById(R.id.iv_five);
        final View inflateView = LayoutInflater.from(this).inflate(R.layout.dialog_view, null);
        etBluetoothTagId = (EditText) inflateView.findViewById(R.id.etDeviceID);


        list.add(firstView);
        list.add(secondView);
        list.add(thirdView);
        list.add(forthView);
        list.add(fifthView);
        adapter = new MyViewPagerAdapter(list);
        viewPager.setAdapter(adapter);

        dots = new ImageView[5];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = (ImageView) llContainer.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setTag(i);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem((Integer) v.getTag());
                }
            });
        }
        dots[0].setEnabled(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setEnabled(true);
                }
                dots[position].setEnabled(false);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        myCount = new MyCount(20 * 1000, 1000);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                myCount.start();
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = true;
                handler.sendMessage(message);

            }
        }, 5000);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (myCount != null) {
            myCount.cancel();
            myCount = null;
        }
    }


    @Override
    protected void onResume() {
        Log.d(Tag, "On Resume!");
        super.onResume();
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(option);
//        getWindow().setNavigationBarColor(Color.TRANSPARENT);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    class MyCount extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvCountTime.setText(millisUntilFinished / 1000 + "");
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(ProductActivity.this, MainActivity.class);
            startActivity(intent);
            ProductActivity.this.finish();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(Tag, "OnWindowFocusChanged");
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(option);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        super.onWindowFocusChanged(hasFocus);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Tag, "On Start!");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Tag, "on Pause!");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Tag, "on Stop!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Tag, "on Restart!");
    }


}
