package com.ontime.newprj1353.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ontime.newprj1353.R;
import com.ontime.newprj1353.adapter.MyListAdapter;
import com.ontime.newprj1353.bean.JsonResult;
import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yafr1hz1 on 2017/8/30.
 */
public class ProductListActivity extends AppCompatActivity {

    @InjectView(R.id.goods_list)
    RecyclerView goodsList;

    private String bluetoothTagId;
    public static String picFiveUrl;
    public static String picFourUrl;
    public static String picThreeUrl;
    public static String picTwoUrl;
    public static String picOneUrl;
    public static String productName;
    public static String productCode;
    //cn.manytag.ishw6003.webservice

    private static final String SERVICE_NAMESPACE = "http://webservice.ishw6003.manytag.cn";
    //URL
    private static final String SERVICE_URL = "http://139.224.104.4:8087/iSHW6003/services/IshowService";
    @InjectView(R.id.button)
    Button button;
    // private   String PROPERTY = "{\"userName\":\"prj1353\",\"passWord\":\"123456\",\"designVersion\":\"desing.000001\",\"softVersion\":\"000008\",\"dataBaseVersion\":\"20170617233733\"}";
    private String PROPERTY = "";
    private static final String METHODNAME = "IshowService1";
    private static final int RESULTCODE = 200;
    private SharedPreferences sp;

    private MyListAdapter reAdapter;  //RecyclerView的适配器
    public static List<JsonResult> list = new ArrayList<>(); //动态存储信息的集合

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Gson gson = new Gson();
                    String jsonResultStr = (String) msg.obj;
                    JsonResult jsonResult = gson.fromJson(jsonResultStr, JsonResult.class);
                    picOneUrl = jsonResult.getData().getShops().get(0).getMajorClasses().get(0).getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts().get(0).getImageFileURL();
                    picTwoUrl = jsonResult.getData().getShops().get(0).getMajorClasses().get(0).getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts().get(0).getColors().get(0).getPrictures().get(0).getImageFileURL();
                    picThreeUrl = jsonResult.getData().getShops().get(0).getMajorClasses().get(0).getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts().get(0).getColors().get(0).getPrictures().get(1).getImageFileURL();
                    picFourUrl = jsonResult.getData().getShops().get(0).getMajorClasses().get(0).getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts().get(0).getColors().get(0).getPrictures().get(2).getImageFileURL();
                    picFiveUrl = jsonResult.getData().getShops().get(0).getMajorClasses().get(0).getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts().get(0).getColors().get(0).getPrictures().get(3).getImageFileURL();

                    productName = jsonResult.getData().getShops().get(0).getMajorClasses().get(0)
                            .getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts()
                            .get(0).getProductName();
                    Log.d("productName-----",productName);
                    productCode = jsonResult.getData().getShops().get(0).getMajorClasses().get(0)
                            .getMiddessClasses().get(0).getGeneralClasses().get(0).getProducts()
                            .get(0).getProductCode();
                    Picasso.with(ProductListActivity.this).load(ProductListActivity.picOneUrl).into(MyListAdapter.MyViewHolder.imageView);

                    MyListAdapter.MyViewHolder.itemName.setText(productName);
                    MyListAdapter.MyViewHolder.itemCode.setText(productCode);

                    //初始化蓝牙标签的显示
                    MyListAdapter.MyViewHolder.bluetoothTagId.setText(sp.getString("BluetoothTagId","暂未绑定标签"));
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //TODO 从json字符串中取出产品id进行显示
    //TODO Gson gson = new Gson();
    //TODO JsonResult jsonResult = gson.fromJson(jsonResultStr, JsonResult.class);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.inject(this);

        goodsList.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        goodsList.setHasFixedSize(true);
        reAdapter = new MyListAdapter(ProductListActivity.this);
        goodsList.setAdapter(reAdapter);

        reAdapter.setOnItemClickListener(new MyListAdapter.MyItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ProductListActivity.this, "点击了第" + position + "个条目", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductListActivity.this, BindBluetoothTagIdActivity.class);
                startActivityForResult(intent, RESULTCODE);
            }
        });

        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

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
    }

    //从绑定标签页面获取到蓝牙标签返回给产品页面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                bluetoothTagId = data.getStringExtra("BluetoothTagId");
                MyListAdapter.MyViewHolder.bluetoothTagId.setText(bluetoothTagId);
                break;
        }
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Intent intent = new Intent(ProductListActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

