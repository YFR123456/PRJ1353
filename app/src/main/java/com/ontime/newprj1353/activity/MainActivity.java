package com.ontime.newprj1353.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.ontime.newprj1353.R;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AutoLayoutActivity {

    @InjectView(R.id.videoview)
    VideoView videoView;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;

    private static final int REQUEST_ENABLE_BT = 1;
    //存储蓝牙标签ID
    private SharedPreferences sp;

    //蓝牙标签Id字符串
    private String BluetoothTagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 隐藏状态栏
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //隐藏系统导航栏
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

        //从存放蓝牙标签Id的SharedPreference中取蓝牙标签Id
        sp = getSharedPreferences("SecondBluetoothTagIds", MODE_PRIVATE);
        BluetoothTagId = sp.getString("SecondBluetoothTagId", "-1");


        //使用此检查确定BLE是否支持在设备上，然后可以有选择性的禁用Ble相关的功能
//        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
//            Toast.makeText(this, "抱歉您的设备不支持ble设备哦", Toast.LENGTH_SHORT).show();
//            finish();
//        }else{
//            Toast.makeText(this, "此设备支持BLE~", Toast.LENGTH_SHORT).show();
//        }

        //获取并初始化蓝牙适配器
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        //确保蓝牙在设备上开启
        if (mBluetoothAdapter == null | !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        //广播过滤器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver, intentFilter);

        //开始扫描
        bleAllScan();

        //播放视频Uri
        Uri uri = Uri.parse("android.resource://com.ontime.prj1353/" + R.raw.video1);

        //设置视频播放控制器
        //videoView.setMediaController(new MediaController(this));

        //设置视频Uri
        videoView.setVideoURI(uri);

        //开始播放视频
        videoView.start();

        //监听视频的播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });


        //播放视频设置为全屏
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);
    }


    @Override
    protected void onResume() {
        super.onResume();
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
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态解除广播
        unregisterReceiver(mBroadcastReceiver);
    }

    //判断手机蓝牙开启状态的广播接收者
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String bleStateStr = null;
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        bleStateStr = "STATE_OFF 手机蓝牙已关闭";
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        bleStateStr = "STATE_TURNING_OFF 手机蓝牙正在关闭";
                        break;
                    case BluetoothAdapter.STATE_ON:
                        bleStateStr = "STATE_ON 手机蓝牙已开启";
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        bleStateStr = "STATE_TURNING_ON 手机蓝牙正在开启";
                        break;
                }
                Toast.makeText(MainActivity.this, bleStateStr, Toast.LENGTH_SHORT).show();
            }
        }
    };

    //封装ble开启扫描方法
    public void bleAllScan() {
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }

    //封装ble关闭扫描方法
    public void bleStopScan() {
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    //ble扫描的结果回调接口
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            String deviceName = device.getName();
            String deviceAddr = device.getAddress();

            //截取蓝牙广播中的返回结果，将其转换成字符串
            byte[] myRecord = Arrays.copyOfRange(scanRecord, 12, 16);
            byte[] byteArray = new byte[BluetoothTagId.length() / 2];
            for (int i = 0; i < byteArray.length; i++) {
                String subStr = BluetoothTagId.substring(2 * i, 2 * i + 2);
                byteArray[i] = (byte) Integer.parseInt(subStr, 16);
            }
            //如果与绑定的标签一致，则跳转至产品页
            if (scanRecord[0] == 2 && Arrays.equals(myRecord, byteArray)) {
                //扫描到结果后，停止扫描
                bleStopScan();
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
            //Log.i("扫描记录", Arrays.toString(scanRecord));
            // Log.i("Tag","搜索到设备： 设备名 = "+deviceName+"，设备Mac地址 = "+deviceAddr+",信号强度 ="+rssi);
        }
    };
}
