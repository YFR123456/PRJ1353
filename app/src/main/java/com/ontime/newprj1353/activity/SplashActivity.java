package com.ontime.newprj1353.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.ontime.newprj1353.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yafr1hz1 on 2017/9/4.
 */

public class SplashActivity extends AppCompatActivity {
    @InjectView(R.id.rl_splash)
    RelativeLayout rlSplash;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        sp = SplashActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initEvent();
    }

    private void initEvent() {
        AlphaAnimation aa = new AlphaAnimation(0f, 1f);
        aa.setDuration(1500);
        aa.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            //渐变动画结束后，执行此方法，跳转对应界面
            @Override
            public void onAnimationEnd(Animation animation) {
                //判断,如果账号名跟密码跟系统设定的一样,就可以直接登录
                if (sp.getString("username", "") == null || !TextUtils.equals(sp.getString("username", ""), "prj1353") || !TextUtils.equals(sp.getString("password", ""), "123456")) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    SplashActivity.this.finish();
                } else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", LoginActivity.usernameString);
                    editor.putString("password", LoginActivity.passwordString);
                    editor.commit();
                    startActivity(new Intent(SplashActivity.this, ProductListActivity.class));
                    SplashActivity.this.finish();
                }
            }
        });
        //让控件开始执行补间动画
        rlSplash.startAnimation(aa);
    }
}
