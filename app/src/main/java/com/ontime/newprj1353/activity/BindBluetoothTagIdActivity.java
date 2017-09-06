package com.ontime.newprj1353.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ontime.newprj1353.R;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BindBluetoothTagIdActivity extends AppCompatActivity {

    @InjectView(R.id.tv_shake)
    TextView tvShake;
    @InjectView(R.id.tv_sentence)
    TextView tvSentence;
    @InjectView(R.id.rl_container)
    RelativeLayout rlContainer;
    @InjectView(R.id.tv_sentence_1)
    TextView tvSentence1;
    @InjectView(R.id.et_bluetoothTagId)
    EditText etBluetoothTagId;
    @InjectView(R.id.rl_container_1)
    RelativeLayout rlContainer1;
    @InjectView(R.id.btn_confirm)
    Button btnConfirm;
    @InjectView(R.id.btn_skip)
    Button btnSkip;

    private BluetoothAdapter mBluetoothAdapter;

    private BluetoothManager mBluetoothManager;

    private static final int REQUEST_ENABLE_BT = 1;

    private SharedPreferences sp;

    public static String bluetoothTagId;

    public static String exBluetoothTagId;

    private String strId;

    private int resultCode = 0;
    public static TextView tvCurrentTagId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_bluetooth_tag_id);
        ButterKnife.inject(this);

        tvCurrentTagId = (TextView) findViewById(R.id.tv_currentTagId);

        sp = getSharedPreferences("BluetoothTagIds", MODE_PRIVATE);
        exBluetoothTagId = sp.getString("BluetoothTagId", "暂无标签绑定");
        tvCurrentTagId.setText(exBluetoothTagId);

        //获取并初始化蓝牙适配器
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        //确保蓝牙在设备上开启
        if (mBluetoothAdapter == null | !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        bleAllScan();
    }

    public void bleAllScan() {
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }

    public void bleStopScan() {
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }


    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            String deviceName = device.getName();
            String deviceAddr = device.getAddress();

            //区间左闭右开
            byte[] byteArray = Arrays.copyOfRange(scanRecord, 12, 16);
            byte[] headArray = Arrays.copyOfRange(scanRecord, 0, 12);
            strId = byteArrayToHexStr(byteArray);

            byte[] matchHeadArray = new byte[]{2, 1, 6, 26, -1, 89, 0, 2, 21, -86, 2, 96};

            if (!exBluetoothTagId.equals(strId)) {
                if (scanRecord[0] == 2 && scanRecord[12] == 3 && scanRecord[13] == 0) {
                    bleStopScan();
                }

                if (Arrays.equals(headArray, matchHeadArray) && scanRecord[29] == 100 && scanRecord[28] == 0) {
                    Log.i("strId", strId);
                    etBluetoothTagId.setText(strId);
                }
            }
            Log.i("BindBluetoothTagId", Arrays.toString(scanRecord));
            // Log.i("Tag","搜索到设备： 设备名 = "+deviceName+"，设备Mac地址 = "+deviceAddr+",信号强度 ="+rssi);
        }
    };

    //16进制字符串转byte数组
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    //重写返回按键--不重写有bug
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BindBluetoothTagIdActivity.this, ProductListActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bleStopScan();
    }

    @OnClick({R.id.btn_confirm, R.id.btn_skip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                //绑定标签
                bluetoothTagId = etBluetoothTagId.getText().toString();
                Log.d("BluetoothTagId", bluetoothTagId);
                if (!TextUtils.isEmpty(bluetoothTagId)) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("BluetoothTagId", bluetoothTagId);
                    editor.commit();
                    Toast.makeText(BindBluetoothTagIdActivity.this, "蓝牙标签" + bluetoothTagId + "绑定成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    //携带蓝牙标签的数据并且传递给产品列表的界面
                    intent.putExtra("BluetoothTagId", etBluetoothTagId.getText().toString());
                    setResult(resultCode, intent);
                    BindBluetoothTagIdActivity.this.finish();
                } else {
                    Toast.makeText(BindBluetoothTagIdActivity.this, "标签不可为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_skip:  //暂时不更换绑定
                Intent intent = new Intent();
                intent.putExtra("BluetoothTagId", sp.getString("BluetoothTagId","暂未绑定标签"));
                setResult(resultCode, intent);
                BindBluetoothTagIdActivity.this.finish();
                break;
        }
    }
}
