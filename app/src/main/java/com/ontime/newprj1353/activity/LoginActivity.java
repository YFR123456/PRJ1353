package com.ontime.newprj1353.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ontime.newprj1353.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yafr1hz1 on 2017/8/30.
 */

public class LoginActivity extends AppCompatActivity {
    @InjectView(R.id.etUsername)
    EditText username;
    @InjectView(R.id.etPassword)
    EditText password;
    @InjectView(R.id.login)
    Button login;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothManager mBluetoothManager;

    private static final int REQUEST_ENABLE_BT = 1;
    private SharedPreferences sp;
    public static String usernameString;
    public static String passwordString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        //默认记住用户名
        username.setText(sp.getString("username", ""));
        password.setText(sp.getString("password", ""));

        //获取并初始化蓝牙适配器
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        //确保蓝牙在设备上开启
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        usernameString = username.getText().toString();
        passwordString = password.getText().toString();
        if (TextUtils.isEmpty(usernameString) || TextUtils.isEmpty(passwordString)) {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", usernameString);
            editor.putString("password", passwordString);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }
}
