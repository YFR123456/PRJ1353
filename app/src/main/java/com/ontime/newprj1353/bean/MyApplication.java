package com.ontime.newprj1353.bean;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by shgl1hz1 on 2017/8/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
