package com.example.y_xl.wan.util;

import android.app.Application;
import android.content.Context;


/**
 * Created by Administrator on 2018/3/26.
 */

public class BaseApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //目的是为了别人获取上下文
        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }
}
